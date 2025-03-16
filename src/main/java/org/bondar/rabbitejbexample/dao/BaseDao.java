package org.bondar.rabbitejbexample.dao;





import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDao<T> {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @PersistenceContext(unitName = "applicationPU")
    protected EntityManager em;


    public void create(T entity) throws Exception{
        try {
            em.persist(entity);
            em.flush();
        } catch (Exception e) {
            LOGGER.error("Rabbit Ejb Service failed to insert message : {}", entity.toString(), e);
            throw new Exception(e);
        }
    }

    public void createForClob(T entity) throws Exception{
        T e = em.merge(entity);
    }

    public T update(T entity) throws Exception{
        T e = em.merge(entity);
        return e;
    }

}