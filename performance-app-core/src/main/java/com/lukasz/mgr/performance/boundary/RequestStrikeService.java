package com.lukasz.mgr.performance.boundary;

import com.lukasz.mgr.api.users.User;
import com.lukasz.mgr.performance.control.requeststrike.RequestStrike;
import com.lukasz.mgr.performance.control.requeststrike.UsersResult;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Lukasz Karmanski
 */

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestStrikeService {

    @Inject
    private RequestStrike requestStrike;

    private Client client = ClientBuilder.newClient();

    @GET
    @Path("/base")
    @Produces(MediaType.TEXT_PLAIN)
    public Response runBaseRequestStrike() {
        return Response.ok("OK").build();
    }

    @GET
    @Path("/single")
    @Produces(MediaType.TEXT_PLAIN)
    public UsersResult getSingleUser(@QueryParam("collectionName") String collectionName) {
        UsersResult result = requestStrike.getRandomUserResult(collectionName);
        return result;
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSingleUserHTML(@QueryParam("collectionName") String collectionName, @QueryParam("count") Long count) {
        long requestsTimeStart = System.currentTimeMillis();
        for(int i = 0 ; i < count ; i++) {
            WebTarget target = client.target("http://localhost:8080/rest/mongo/user/random");
            User user = target.queryParam("collectionName", collectionName).request().get().readEntity(User.class);
            String uuid = user.getUuid();
        }
        long requestsTimeEnd = System.currentTimeMillis();
        return "<h5>RESULT:<br>" +
                "separated requests: " + count + "<br>" +
                "time: " + String.valueOf(requestsTimeEnd - requestsTimeStart) + " ms <br>" +
                "avg request time: " + String.valueOf((requestsTimeEnd - requestsTimeStart)/count) + " ms <br></h5>";
    }

    @GET
    @Path("/sequence")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUsersInSequence() throws IOException, URISyntaxException {

        String DB = "users_count_100000";
        User user1 = requestStrike.getRandomUser(DB);
        User user2 = requestStrike.getRandomUser(DB);
        User user3 = requestStrike.getRandomUser(DB);
        User user4 = requestStrike.getRandomUser(DB);
        User user5 = requestStrike.getRandomUser(DB);

        int sum = user1.getAge() + user2.getAge() + user3.getAge() + user4.getAge() + user5.getAge();

        InputStream inputStream = RequestStrikeService.class.getClassLoader().getResourceAsStream("file.txt");
        Optional<String> num = new BufferedReader(new InputStreamReader(inputStream)).lines()
                .filter(StringUtils::isNumeric)
                .findFirst();
        inputStream.close();

        if(!num.isPresent()) {
            throw new RuntimeException("Cannot find number in file");
        }

        int numeric1 = Integer.parseInt(num.get());
        int primesSize1 = prime(numeric1);

        InputStream inputStream2 = RequestStrikeService.class.getClassLoader().getResourceAsStream("file2.txt");
        long start = System.currentTimeMillis();
        Optional<String> num2 = new BufferedReader(new InputStreamReader(inputStream2)).lines()
                .filter(StringUtils::isNumeric)
                .findFirst();
        inputStream2.close();

        if(!num2.isPresent()) {
            throw new RuntimeException("Cannot find number in file");
        }

        int numeric2 = Integer.parseInt(num2.get());
        int primesSize2 = prime(numeric2);

        return String.valueOf(System.currentTimeMillis() - start)
                + " num: " + num.orElse("") + " " + num2.orElse("") + " age sum: " + sum
                + " prime sizes: " + primesSize1 + ", " + primesSize2;
    }

    private int prime(int count) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < count; i++) {
            boolean isPrimeNumber = true;

            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            }

            if (isPrimeNumber) {
                primes.add(i);
            }
        }
        return primes.size();
    }

}
