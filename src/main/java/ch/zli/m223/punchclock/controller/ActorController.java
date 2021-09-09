package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import io.quarkus.oidc.IdToken;
import org.eclipse.microprofile.jwt.JsonWebToken;

import ch.zli.m223.punchclock.domain.Actor;
import ch.zli.m223.punchclock.service.ActorService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/actors")
@Tag(name = "Actors", description = "Handling of actors")
public class ActorController {

    @Inject
    ActorService actorService;

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
    public List<Actor> list() {
        return actorService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Actor getSingleActor(Long id){
        return actorService.getActorById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Actor add(Actor actor) {
        return actorService.createActor(actor);
    }

    @DELETE
    @Path("/{id}")
    public void deleteActor(@PathParam Long id){
        actorService.delete(id);
    }

    @PUT
    public void update(Actor actor){
        actorService.update(actor);
    }
}
