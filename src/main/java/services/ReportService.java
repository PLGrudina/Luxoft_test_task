package services;

import models.AverageRepStat;
import models.Report;

import java.io.IOException;

/**
 * Created by PavelGrudina on 17.06.2017.
 */
public interface ReportService {

    Report save (Report report);

    void delete (long id);

    Report splitByLines(String name, String url) throws IOException;

    AverageRepStat averageStat(long reportId);

}
