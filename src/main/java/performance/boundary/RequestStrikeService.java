package performance.boundary;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Lukasz Karmanski
 */

@Path("/request-strike/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestStrikeService {

    @GET
    @Path("/base")
    @Produces(MediaType.TEXT_PLAIN)
    public Response runBaseRequestStrike() {
        return Response.ok("OK").build();
    }

}
