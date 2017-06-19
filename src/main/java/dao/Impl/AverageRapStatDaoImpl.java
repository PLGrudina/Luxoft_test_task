package dao.Impl;

import dao.AverageRepStatDao;
import models.AverageRepStat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
@Repository
public class AverageRapStatDaoImpl implements AverageRepStatDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AverageRepStat save(AverageRepStat stat) {
        entityManager.persist(stat);
        return stat;
    }

    @Override
    public AverageRepStat update(AverageRepStat stat) {
        entityManager.persist(stat);
        return stat;
    }

    @Override
    public AverageRepStat saveOrUpdate(AverageRepStat stat) {
        if (stat.getId() > 0) {
            update(stat);
        } else {
            save(stat);
        }
        return stat;
    }

    @Override
    public void delete(long id) {
        AverageRepStat stat = entityManager.find(AverageRepStat.class, id);
        entityManager.remove(stat);
    }
}
