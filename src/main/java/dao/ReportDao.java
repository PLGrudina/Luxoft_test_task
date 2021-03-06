package dao;

import models.Report;

import java.util.List;

/**
 * Created by PavelGrudina on 16.06.2017..
 */
public interface ReportDao {

    Report save(Report report);

    Report update(Report report);

    Report saveOrUpdate(Report report);

    void delete(long id);

    Report findById (long id);

    List<Report> allReports ();

}
