package dao;

import models.Line;

/**
 * Created by PavelGrudina on 16.06.2017.
 */
public interface LineDao {

    Line save (Line line);
    Line update (Line line);
    Line saveOrUpdate (Line line);
    void delete (long id);
}
