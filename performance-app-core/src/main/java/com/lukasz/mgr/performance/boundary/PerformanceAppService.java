package com.lukasz.mgr.performance.boundary;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Lukasz Karmanski
 */
@Path("/performance")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PerformanceAppService {


    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHeartBeat() {
        return Response.ok("Java-Performance-Application Status: OK").build();
    }

}
