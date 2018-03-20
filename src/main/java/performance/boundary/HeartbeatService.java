package performance.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Lukasz Karmanski
 */
@Path("/v1/heartbeat")
public class HeartbeatService {

    @Produces({ MediaType.TEXT_PLAIN })
    @GET
    public Response getHeartBeat() {
        return Response.ok("OK").build();
    }

}
