package consoleApp;

import consoleApp.dao.DbConnector;
import consoleApp.dao.LineDaoConsoleImpl;
import consoleApp.dao.ReportDaoConsoleImpl;
import consoleApp.services.StatCalc;
import models.Report;

import java.io.IOException;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class ConsoleAppMain {

    public static DbConnector dbConnector = new DbConnector();

    public static void main(String[] args) {
        ReportDaoConsoleImpl reportDaoConsole = new ReportDaoConsoleImpl();
        LineDaoConsoleImpl lineDaoConsole = new LineDaoConsoleImpl();
        StatCalc statCalc = new StatCalc();


        Report report = new Report();
        report.setName("Fate");
        report.setUrl("C:\\Users\\PavelGrudina\\IdeaProjects\\MyProject\\testtask\\src\\main\\webapp\\static\\text\\Fate.txt");

        try {
            report = statCalc.splitByLines(report.getName(), report.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        statCalc.lineStatisticCalculate(lineDaoConsole.findAllLinesByRepId(report.getId()));
        statCalc.averageStat(report.getId());
        report = reportDaoConsole.findById(report.getId());

        System.out.println(report.getName() + " >>> " + report.getReportStatistics());

    }
}
