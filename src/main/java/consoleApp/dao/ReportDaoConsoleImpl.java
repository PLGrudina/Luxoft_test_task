package consoleApp.dao;

import consoleApp.ConsoleAppMain;
import models.Report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class ReportDaoConsoleImpl {

    private DbConnector dbConnect;

    private LineDaoConsoleImpl lineDaoConsole = new LineDaoConsoleImpl();

    private AverageRapStatDaoImpl averageRapStatDao = new AverageRapStatDaoImpl();

    public ReportDaoConsoleImpl() {
        dbConnect = ConsoleAppMain.dbConnector;
    }

    public Report save(Report report) {
        try {
            PreparedStatement preparedStatement = dbConnect.con.prepareStatement("INSERT INTO PUBLIC.REPORTS(ID,NAME,URL) VALUES (?,?,?)");
            long id = (long) (Math.random() * 1000);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, report.getName());
            preparedStatement.setString(3, report.getUrl());

            preparedStatement.executeUpdate();


            PreparedStatement preparedStatementBack = dbConnect.con.prepareStatement("SELECT * FROM PUBLIC.REPORTS WHERE REPORTS.ID = ?");
            preparedStatementBack.setLong(1, id);
            ResultSet resultSet = preparedStatementBack.executeQuery();

            if (resultSet.next()) {
                report.setId(resultSet.getLong("ID"));
                report.setName(resultSet.getString("NAME"));
                report.setUrl(resultSet.getString("URL"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }

    public Report update(Report report) {

        try {
            PreparedStatement preparedStatement = dbConnect.con.prepareStatement("UPDATE PUBLIC.REPORTS SET NAME=?,URL=?,REPORTSTATISTICS_ID=? WHERE REPORTS.ID = ?");
            preparedStatement.setString(1, report.getName());
            preparedStatement.setString(2, report.getUrl());
            preparedStatement.setLong(3, report.getReportStatistics().getId());
            preparedStatement.setLong(4, report.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }

    public Report saveOrUpdate(Report report) {
        if (report.getId() > 0) {
            update(report);
        } else {
            save(report);
        }
        return report;
    }

    public void delete(long id) {

    }

    public Report findById(long id) {

        Report report = new Report();

        try {
            PreparedStatement preparedStatement = dbConnect.con.prepareStatement("SELECT * FROM PUBLIC.REPORTS WHERE REPORTS.ID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                report.setId(resultSet.getLong("ID"));
                report.setName(resultSet.getString("NAME"));
                report.setUrl(resultSet.getString("URL"));
                report.setAllLines(lineDaoConsole.findAllLinesByRepId(report.getId()));
                report.setReportStatistics(averageRapStatDao.findAverageRepStByReportId(report.getId()));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }
}
