package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import io.quarkus.oidc.IdToken;
import org.eclipse.microprofile.jwt.JsonWebToken;

import ch.zli.m223.punchclock.domain.Series;
import ch.zli.m223.punchclock.service.SeriesService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/series")
@Tag(name = "Series", description = "Handling of series")
public class SeriesController {

    @Inject
    SeriesService seriesService;

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
    public List<Series> list() {
        return seriesService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Series getSingleSeries(Long id){
        return seriesService.getSeriesById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Series add(Series series) {
        return seriesService.createSeries(series);
    }

    @DELETE
    @Path("/{id}")
    public void deleteSeries(@PathParam Long id){
        seriesService.delete(id);
    }

    @PUT
    public void update(Series series){
        seriesService.update(series);
    }
}
