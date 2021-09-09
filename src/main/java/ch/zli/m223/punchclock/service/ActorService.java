package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Actor;

@ApplicationScoped
public class ActorService {
    @Inject
    private EntityManager entityManager;

    public ActorService() {
    }

    @Transactional
    public Actor createActor(Actor actor) {
        entityManager.persist(actor);
        return actor;
    }

    @SuppressWarnings("unchecked")
    public List<Actor> findAll() {
        var query = entityManager.createQuery("FROM Actor");
        return query.getResultList();
    }

    public Actor getActorById(Long id) {
        return entityManager.find(Actor.class, id);
    }

    @Transactional
    public void delete(Long id){
        Actor actor = getActorById(id);
        entityManager.remove(actor);
    }
    @Transactional
    public void update(Actor actor){

    }
}
