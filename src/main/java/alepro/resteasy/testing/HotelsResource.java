package alepro.resteasy.testing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author alepro
 */
@Path("/hotels")
public class HotelsResource {
    
    @GET
    public Response m() {
        return Response.ok().build();
    }
    
}
