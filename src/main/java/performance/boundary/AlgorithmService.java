package performance.boundary;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import performance.control.algorithm.PerformanceAlgorithm;

/**
 * Created by Lukasz Karmanski
 */
@Path("/algorithm")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlgorithmService {

    @Inject
    private PerformanceAlgorithm performanceAlgorithm;

    @GET
    @Path("/base")
    @Produces(MediaType.TEXT_PLAIN)
    public Response runBaseAlgorithm() {
        return Response.ok("OK").build();
    }

    @GET
    @Path("/external")
    @Produces(MediaType.TEXT_PLAIN)
    public Response runExternalAlgorithm() {
        String result = performanceAlgorithm.run();
        return Response.ok(result).build();
    }

    //test

}
