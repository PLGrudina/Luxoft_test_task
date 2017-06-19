package consoleApp;

import models.Line;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class LineDao {

    private DbConnector dbConnector;

    public LineDao() {
        dbConnector = new DbConnector();
    }

    public Line save (Line line) {

    }

    public Line update (Line line) {

    }

    public Line saveOrUpdate(Line line) {

        if (line.getId() > 0) {
            update(line);
        } else {
            save(line);
        }
        return line;
    }

    public void delete(long id){

    }
}
