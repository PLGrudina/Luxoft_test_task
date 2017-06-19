package dao;

import models.AverageRepStat;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public interface AverageRepStatDao {

    AverageRepStat save (AverageRepStat stat);
    AverageRepStat update (AverageRepStat stat);
    AverageRepStat saveOrUpdate (AverageRepStat stat);
    void delete (long id);
}
