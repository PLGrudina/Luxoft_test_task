package services;

import models.AverageRepStat;
import models.Report;

import java.io.IOException;
import java.util.List;

/**
 * Created by PavelGrudina on 17.06.2017.
 */
public interface ReportService {

    Report save (Report report);

    void delete (long id);

    Report splitByLines(String name, String url) throws IOException;

    AverageRepStat averageStat(long reportId);

    List<Report> getAllReports ();

    Report createFromUrl (String name, String url);

}
