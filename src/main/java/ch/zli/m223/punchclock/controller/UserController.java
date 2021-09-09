package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import io.quarkus.oidc.IdToken;
import org.eclipse.microprofile.jwt.JsonWebToken;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/users")
@Tag(name = "users", description = "Handling of users")
public class UserController {

    @Inject
    UserService userService;

    @IdToken
    JsonWebToken idToken;


    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        return
                "<html>\n" +
                        "    <body>\n" +
                        "        <h1>Hello " + idToken.getClaim("email") + "</h1>\n" +
                        "    </body>\n"+
                        "</html>\n";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSingleUser(Long id){
        return userService.getUserById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User add(User user) {
        return userService.createUser(user);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam Long id){
        userService.delete(id);
    }

    @PUT
    public void update(User user){
        userService.update(user);
    }
}
