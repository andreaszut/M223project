package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import io.quarkus.oidc.IdToken;
import org.eclipse.microprofile.jwt.JsonWebToken;

import ch.zli.m223.punchclock.domain.Movie;
import ch.zli.m223.punchclock.service.MovieService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/movies")
@Tag(name = "Movies", description = "Handling of movies")
public class MovieController {

    @Inject
    MovieService movieService;

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
    public List<Movie> list() {
        return movieService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getSingleMovie(Long id){
        return movieService.getMovieById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Movie add(Movie movie) {
        return movieService.createMovie(movie);
    }

    @DELETE
    @Path("/{id}")
    public void deleteMovie(@PathParam Long id){
        movieService.delete(id);
    }

    @PUT
    public void update(Movie movie){
        movieService.update(movie);
    }
}
