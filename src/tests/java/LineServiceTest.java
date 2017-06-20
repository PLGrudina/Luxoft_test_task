import config.PersistenceConfig;
import models.Line;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import services.Impl.LineServiceImpl;
import services.LineService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PavelGrudina on 20.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, LineServiceImpl.class})
public class LineServiceTest {

    @Autowired
    LineService lineService;

    List<Line> lineList = new ArrayList<>();
    Line lineTest = new Line();
    Line lineForCalc = new Line();

    @Before
    public void setUp() throws Exception {

        lineTest.setFullLine("You must process it");
        lineTest.setAverageWordLength(4);
        lineTest.setLineLength(19);
        lineTest.setLongestWord("process");
        lineTest.setShortestWord("it");
        lineService.save(lineTest);

        lineForCalc.setFullLine("You must process it");
        lineService.save(lineForCalc);
        lineList.add(lineForCalc);

    }

    @Test
    @Transactional
    public void lineStatisticCalculate() {
        lineService.lineStatisticCalculate(lineList);
        Line lineExpected = new Line();
        for (Line lineIter : lineList) {
            lineExpected = lineIter;
        }
        Assert.assertEquals(lineExpected,lineTest);
    }
}
