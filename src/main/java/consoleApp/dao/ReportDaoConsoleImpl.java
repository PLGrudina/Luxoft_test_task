package consoleApp.dao;

import consoleApp.ConsoleAppMain;
import models.Report;

import javax.persistence.EntityManager;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class ReportDaoConsoleImpl {

    public Report save(Report report) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(report);

        entityManager.getTransaction().commit();
        report = entityManager.find(Report.class, report.getId());
        entityManager.close();

        return report;
    }

    public Report update(Report report) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        report = entityManager.merge(report);

        entityManager.getTransaction().commit();
        entityManager.close();

        return report;
    }

    public Report saveOrUpdate(Report report) {
        if (report.getId() > 0) {
            update(report);
        } else {
            save(report);
        }
        return report;
    }

    public void delete(long id) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Report report = entityManager.find(Report.class, id);

        entityManager.remove(report);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public Report findById(long id) {
        EntityManager entityManager = ConsoleAppMain.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Report report = entityManager.find(Report.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();

        return report;
    }

}
