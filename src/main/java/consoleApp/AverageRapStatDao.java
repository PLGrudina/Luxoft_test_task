package consoleApp;

import models.AverageRepStat;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class AverageRapStatDao {

    private DbConnector dbConnector;

    public AverageRapStatDao() {
        dbConnector = new DbConnector();
    }

    public AverageRepStat save(AverageRepStat averageRepStat) {

    }

    public AverageRepStat update(AverageRepStat averageRepStat) {

    }

    public AverageRepStat saveOrUpdate(AverageRepStat stat) {
        if (stat.getId() > 0) {
            update(stat);
        } else {
            save(stat);
        }
        return stat;
    }

    public void delete (long id) {

    }
}


