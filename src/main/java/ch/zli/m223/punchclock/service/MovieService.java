package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Movie;

@ApplicationScoped
public class MovieService {
    @Inject
    private EntityManager entityManager;

    public MovieService() {
    }

    @Transactional
    public Movie createMovie(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }

    @SuppressWarnings("unchecked")
    public List<Movie> findAll() {
        var query = entityManager.createQuery("FROM Movie");
        return query.getResultList();
    }

    public Movie getMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    @Transactional
    public void delete(Long id){
        Movie movie = getMovieById(id);
        entityManager.remove(movie);
    }
    @Transactional
    public void update(Movie movie){

    }
}
