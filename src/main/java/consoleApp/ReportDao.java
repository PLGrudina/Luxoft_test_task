package consoleApp;

import models.Report;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class ReportDao {

    private DbConnector dbConnector;

    public ReportDao (){
        dbConnector = new DbConnector();
    }

    public Report save(Report report){

    }

    public Report update(Report report){

    }

    public Report saveOrUpdate(Report report) {
        if (report.getId() > 0) {
            update(report);
        } else {
            save(report);
        }
        return report;
    }


    public void delete(long id){

    }

    public Report findById(long id) {

    }

}
