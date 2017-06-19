package consoleApp;

import consoleApp.dao.AverageRapStatDaoImpl;
import consoleApp.dao.LineDaoConsoleImpl;
import consoleApp.dao.ReportDaoConsoleImpl;
import consoleApp.services.StatCalc;
import models.Report;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class ConsoleAppMain {

    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-task");

    public static void main(String[] args) {
        ReportDaoConsoleImpl reportDaoConsole = new ReportDaoConsoleImpl();
        StatCalc statCalc = new StatCalc();


        Report report = new Report();
        report.setName("Fate");
        report.setUrl("C:\\Users\\PavelGrudina\\IdeaProjects\\MyProject\\testtask\\src\\main\\resources\\text\\Fate.txt");

        try {
            report = statCalc.splitByLines(report.getName(), report.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        statCalc.lineStatisticCalculate(report.getAllLines());
        statCalc.averageStat(report.getId());
        report = reportDaoConsole.findById(report.getId());

        System.out.println(report.getName() + " >>> " + report.getReportStatistics());

    }
}
