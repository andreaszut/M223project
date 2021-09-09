package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Series;

@ApplicationScoped
public class SeriesService {
    @Inject
    private EntityManager entityManager;

    public SeriesService() {
    }

    @Transactional
    public Series createSeries(Series series) {
        entityManager.persist(series);
        return series;
    }

    @SuppressWarnings("unchecked")
    public List<Series> findAll() {
        var query = entityManager.createQuery("FROM Series");
        return query.getResultList();
    }

    public Series getSeriesById(Long id) {
        return entityManager.find(Series.class, id);
    }

    @Transactional
    public void delete(Long id){
        Series series = getSeriesById(id);
        entityManager.remove(series);
    }
    @Transactional
    public void update(Series series){

    }
}
