package performance.boundary;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import performance.control.algorithm.PerformanceAlgorithm;
import performance.control.algorithm.result.DijkstraResult;

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

    @POST
    @Path("/dijkstra")
    public DijkstraResult runDijkstraAlgorithm(@QueryParam("collectionName") @DefaultValue("dijkstra_nodes_30") String collectionName) {
        DijkstraResult result = performanceAlgorithm.runDijkstraAlgorithm(collectionName);
        return result;
    }

}
