package consoleApp.dao;

import consoleApp.ConsoleAppMain;
import models.Line;

import javax.persistence.EntityManager;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class LineDaoConsoleImpl {

    public Line save(Line line) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(line);

        entityManager.getTransaction().commit();
        line = entityManager.find(Line.class, line.getId());
        entityManager.close();

        return line;
    }

    public Line update(Line line) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        line = entityManager.merge(line);

        entityManager.getTransaction().commit();
        entityManager.close();

        return line;
    }

    public Line saveOrUpdate(Line line) {
        if (line.getId() > 0) {
            update(line);
        } else {
            save(line);
        }
        return line;
    }

    public void delete(long id) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Line line = entityManager.find(Line.class, id);

        entityManager.remove(line);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
