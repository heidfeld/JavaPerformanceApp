package com.lukasz.mgr.performance.boundary;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.lukasz.mgr.performance.control.algorithm.PerformanceAlgorithm;
import com.lukasz.mgr.performance.control.algorithm.result.Dijkstra2Result;
import com.lukasz.mgr.performance.control.algorithm.result.DijkstraResult;

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

    @POST
    @Path("/dijkstra2")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Dijkstra2Result runDijkstra2Algorithm(@FormParam("collectionName") @DefaultValue("dijkstra_nodes_30") String collectionName,
                                                 @FormParam("fromNode") String fromNode,
                                                 @FormParam("toNode") String toNode) {
        Dijkstra2Result result = performanceAlgorithm.runDijkstra2Algorithm(collectionName, fromNode, toNode);
        return result;
    }

}
