package consoleApp.dao;

import consoleApp.ConsoleAppMain;
import models.AverageRepStat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class AverageRapStatDaoImpl {

    private DbConnector dbConnect;

    public AverageRapStatDaoImpl() {
        dbConnect = ConsoleAppMain.dbConnector;
    }


    public AverageRepStat save(AverageRepStat stat, long reportId) {
        try {
            PreparedStatement preparedStatement = dbConnect.con.prepareStatement("INSERT INTO PUBLIC.STATISTIC(ID,AVERAGEWORDLENGTH,LINESCOUNT,LONGESTWORD,SHORTESTWORD) VALUES (?,?,?,?,?)");
            long id = (long) (Math.random() * 1000);
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, stat.getAverageWordLength());
            preparedStatement.setInt(3, stat.getLinesCount());
            preparedStatement.setString(4, stat.getLongestWord());
            preparedStatement.setString(5, stat.getShortestWord());

            preparedStatement.executeUpdate();

            PreparedStatement preparedStatementBack = dbConnect.con.prepareStatement("SELECT * FROM PUBLIC.STATISTIC WHERE STATISTIC.ID = ?");
            preparedStatementBack.setLong(1, id);
            ResultSet resultSet = preparedStatementBack.executeQuery();

            if (resultSet.next()) {
                stat.setId(resultSet.getLong("ID"));
                stat.setAverageWordLength(resultSet.getInt("AVERAGEWORDLENGTH"));
                stat.setLinesCount(resultSet.getInt("LINESCOUNT"));
                stat.setLongestWord(resultSet.getString("LONGESTWORD"));
                stat.setShortestWord(resultSet.getString("SHORTESTWORD"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stat;
    }

    public AverageRepStat update(AverageRepStat stat) {

        try {
            PreparedStatement preparedStatement = dbConnect.con.prepareStatement("UPDATE PUBLIC.STATISTIC SET AVERAGEWORDLENGTH=?,LINESCOUNT=?,LONGESTWORD=?,SHORTESTWORD=? WHERE STATISTIC.ID = ?");
            preparedStatement.setInt(1, stat.getAverageWordLength());
            preparedStatement.setInt(2, stat.getLinesCount());
            preparedStatement.setString(3, stat.getLongestWord());
            preparedStatement.setString(4, stat.getShortestWord());
            preparedStatement.setLong(5, stat.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stat;
    }

    public AverageRepStat saveOrUpdate(AverageRepStat stat, long reportId) {
        if (stat.getId() > 0) {
            update(stat);
        } else {
            save(stat, reportId);
        }
        return stat;
    }

    public AverageRepStat findAverageRepStByReportId(long id) {
        AverageRepStat stat = new AverageRepStat();
        long statId = 0;
        try {

            PreparedStatement preparedStatement = dbConnect.con.prepareStatement("SELECT (REPORTSTATISTICS_ID) FROM PUBLIC.REPORTS WHERE REPORTS.ID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSetRepId = preparedStatement.executeQuery();

            if (resultSetRepId.next()) {
                statId = resultSetRepId.getLong("REPORTSTATISTICS_ID");

            }

            PreparedStatement preparedStatementBack = dbConnect.con.prepareStatement("SELECT * FROM PUBLIC.STATISTIC WHERE STATISTIC.ID = ?");
            preparedStatementBack.setLong(1, statId);
            ResultSet resultSet = preparedStatementBack.executeQuery();

            if (resultSet.next()) {
                stat.setId(resultSet.getLong("ID"));
                stat.setAverageWordLength(resultSet.getInt("AVERAGEWORDLENGTH"));
                stat.setLinesCount(resultSet.getInt("LINESCOUNT"));
                stat.setLongestWord(resultSet.getString("LONGESTWORD"));
                stat.setShortestWord(resultSet.getString("SHORTESTWORD"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stat;
    }

}


