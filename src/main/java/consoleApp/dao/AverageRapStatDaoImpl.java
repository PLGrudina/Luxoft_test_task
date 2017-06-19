package consoleApp.dao;

import consoleApp.ConsoleAppMain;
import models.AverageRepStat;

import javax.persistence.EntityManager;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class AverageRapStatDaoImpl {

    public AverageRepStat save(AverageRepStat stat) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(stat);

        entityManager.getTransaction().commit();
        stat = entityManager.find(AverageRepStat.class, stat.getId());
        entityManager.close();

        return stat;
    }

    public AverageRepStat update(AverageRepStat stat) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        stat = entityManager.merge(stat);

        entityManager.getTransaction().commit();
        entityManager.close();

        return stat;
    }

    public AverageRepStat saveOrUpdate(AverageRepStat stat) {
        if (stat.getId() > 0) {
            update(stat);
        } else {
            save(stat);
        }
        return stat;
    }

    public void delete(long id) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        AverageRepStat averageRepStat = entityManager.find(AverageRepStat.class, id);

        entityManager.remove(averageRepStat);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


