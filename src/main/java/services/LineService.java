package services;

import models.Line;

import java.util.List;

/**
 * Created by PavelGrudina on 17.06.2017.
 */
public interface LineService {

    Line save (Line line);

    void delete (long id);

    void lineStatisticCalculate (List<Line> allReportLines);
}
