package dao.Impl;

import dao.LineDao;
import models.Line;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by PavelGrudina on 17.06.2017.
 */
@Repository
public class LineDaoImpl implements LineDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Line save(Line line) {
        entityManager.persist(line);
        return line;
    }

    @Override
    public Line update(Line line) {
        entityManager.merge(line);
        return line;
    }

    @Override
    public Line saveOrUpdate(Line line) {

        if (line.getId() > 0) {
            update(line);
        } else {
            save(line);
        }
        return line;
    }

    @Override
    public void delete(long id) {
        Line line = entityManager.find(Line.class, id);
        entityManager.remove(line);

    }
}
