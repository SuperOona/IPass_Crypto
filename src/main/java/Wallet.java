import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Wallet {

    @DELETE
    @RolesAllowed("admin")
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sellStock(@PathParam())
}
