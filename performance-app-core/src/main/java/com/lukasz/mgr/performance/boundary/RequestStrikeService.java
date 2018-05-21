package com.lukasz.mgr.performance.boundary;

import com.lukasz.mgr.api.users.User;
import com.lukasz.mgr.performance.control.requeststrike.RequestStrike;
import com.lukasz.mgr.performance.control.requeststrike.UsersResult;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

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
    public UsersResult getSingleUser(@FormParam("collectionName") String collectionName) {
        UsersResult result = requestStrike.getRandomUser(collectionName);
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

}
