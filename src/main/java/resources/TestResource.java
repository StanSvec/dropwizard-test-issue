package resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import util.IPAddress;

@Path("/api")
public class TestResource {

    @GET
    public Response getResponse(@Context IPAddress ipAddress) {
        System.out.println(ipAddress);
        return Response.ok().build();
    }
}
