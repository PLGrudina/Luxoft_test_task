import config.PersistenceConfig;
import consoleApp.dao.LineDaoConsoleImpl;
import consoleApp.dao.ReportDaoConsoleImpl;
import consoleApp.services.StatCalc;
import models.Line;
import models.Report;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PavelGrudina on 24.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, StatCalc.class})
public class StatCalcTest {

    StatCalc statCalc = new StatCalc();
    LineDaoConsoleImpl lineDaoConsole = new LineDaoConsoleImpl();
    ReportDaoConsoleImpl reportDaoConsole = new ReportDaoConsoleImpl();

    List<Line> lineList = new ArrayList<>();
    Line lineTest = new Line();
    Line lineForCalc = new Line();
    Report report = new Report();

    @Before
    public void setUp() throws Exception {

        lineTest.setFullLine("You must process it");
        lineTest.setAverageWordLength(4);
        lineTest.setLineLength(19);
        lineTest.setLongestWord("process");
        lineTest.setShortestWord("it");
        report.setId(777);
        reportDaoConsole.save(report);
        lineDaoConsole.save(lineTest, report.getId());

        lineForCalc.setFullLine("You must process it");
        report.setId(333);
        reportDaoConsole.save(report);
        lineDaoConsole.save(lineForCalc, report.getId());
        lineList.add(lineForCalc);

    }

    @Test
    public void lineStatisticCalculate() {
        statCalc.lineStatisticCalculate(lineList);
        Line lineExpected = new Line();
        for (Line lineIter : lineList) {
            lineExpected = lineIter;
        }
        Assert.assertEquals(lineExpected, lineTest);
    }

    @Test
    public void allLinesStatCalc() {
        Report report = new Report();
        report.setId(3659);
        report.setName("Test");
        report.setAllLines(lineList);
        reportDaoConsole.save(report);
        statCalc.lineStatisticCalculate(report.getAllLines());

        for (Line lineExpected : report.getAllLines()) {
            Assert.assertNotNull(lineExpected.getAverageWordLength());
            Assert.assertNotNull(lineExpected.getLineLength());
            Assert.assertNotNull(lineExpected.getLongestWord());
            Assert.assertNotNull(lineExpected.getShortestWord());
        }
    }
}

