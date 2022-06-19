import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.AbstractMap;
import java.util.Calendar;

public class Authentication {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LogonRequest logonRequest){
        try {
            String role = User.valifateLogin(logonRequest.username, logonRequest.password);
            if (role == null) throw new IllegalAccessException("no user found");

            String token = createToken(logonRequest.username, role);
            return Response.ok(new AbstractMap.SimpleEntry<>("JWT", token)).build();

        } catch (JwtException | IllegalAccessException e){
            return Response.status(Response.Status.UNAUTHORIZED).build(); //geen body
        }
    }

    private String createToken(String username, String role) throws JwtException{
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
