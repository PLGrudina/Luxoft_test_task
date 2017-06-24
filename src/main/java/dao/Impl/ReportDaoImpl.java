package dao.Impl;

import dao.ReportDao;
import models.Report;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by PavelGrudina on 17.06.2017.
 */
@Repository
public class ReportDaoImpl implements ReportDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Report save(Report report) {
        entityManager.persist(report);
        return report;
    }

    @Override
    public Report update(Report report) {
        entityManager.merge(report);
        return report;
    }

    @Override
    public Report saveOrUpdate(Report report) {
        if (report.getId() > 0) {
            update(report);
        } else {
            save(report);
        }
        return report;
    }

    @Override
    public void delete(long id) {
        Report report = entityManager.find(Report.class, id);
        entityManager.remove(report);
    }

    @Override
    public Report findById(long id) {
        return entityManager.find(Report.class, id);
    }

    @Override
    public List<Report> allReports() {
        List<Report> resultList = entityManager.createQuery("from Report", Report.class).getResultList();
        return resultList;
    }


}
