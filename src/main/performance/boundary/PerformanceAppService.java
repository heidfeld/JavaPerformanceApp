package performance.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Lukasz Karmanski
 */

@Path("")
public class PerformanceAppService {

    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String status() {
        return "Status: OK";
    }

}
