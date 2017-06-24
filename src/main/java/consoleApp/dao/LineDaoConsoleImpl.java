package consoleApp.dao;

import consoleApp.ConsoleAppMain;
import models.Line;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class LineDaoConsoleImpl {

    private DbConnector dbConnect;

    public LineDaoConsoleImpl() {
        dbConnect = ConsoleAppMain.dbConnector;
    }

    public Line save(Line line, long reportId) {
        try {
            PreparedStatement preparedStatement = dbConnect.con.prepareStatement("INSERT INTO PUBLIC.LINES(ID,AVERAGEWORDLENGTH,FULLLINE,LINELENGTH,LONGESTWORD,SHORTESTWORD) VALUES (?,?,?,?,?,?)");
            long id = (long) (Math.random() * 1000000);
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, line.getAverageWordLength());
            preparedStatement.setString(3, line.getFullLine());
            preparedStatement.setInt(4, line.getLineLength());
            preparedStatement.setString(5, line.getLongestWord());
            preparedStatement.setString(6, line.getShortestWord());

            preparedStatement.executeUpdate();

            PreparedStatement preparedStatementBack = dbConnect.con.prepareStatement("SELECT * FROM PUBLIC.LINES WHERE LINES.ID = ?");
            preparedStatementBack.setLong(1, id);
            ResultSet resultSet = preparedStatementBack.executeQuery();

            if (resultSet.next()) {
                line.setId(resultSet.getLong("ID"));
                line.setAverageWordLength(resultSet.getInt("AVERAGEWORDLENGTH"));
                line.setFullLine(resultSet.getString("FULLLINE"));
                line.setLineLength(resultSet.getInt("LINELENGTH"));
                line.setLongestWord(resultSet.getString("LONGESTWORD"));
                line.setShortestWord(resultSet.getString("SHORTESTWORD"));
            }

            PreparedStatement preparedStatementLR = dbConnect.con.prepareStatement("INSERT INTO PUBLIC.REPORTS_LINES(REPORT_ID, ALLLINES_ID) VALUES (?,?)");
            preparedStatementLR.setLong(1, reportId);
            preparedStatementLR.setLong(2, line.getId());
            preparedStatementLR.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }

        return line;
    }

    public Line update(Line line) {

        try {
            PreparedStatement preparedStatement = dbConnect.con.prepareStatement("UPDATE PUBLIC.LINES SET AVERAGEWORDLENGTH=?,FULLLINE=?,LINELENGTH=?,LONGESTWORD=?,SHORTESTWORD=? WHERE LINES.ID = ?");
            preparedStatement.setInt(1, line.getAverageWordLength());
            preparedStatement.setString(2, line.getFullLine());
            preparedStatement.setInt(3, line.getLineLength());
            preparedStatement.setString(4, line.getLongestWord());
            preparedStatement.setString(5, line.getShortestWord());
            preparedStatement.setLong(6, line.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return line;
    }

    public List<Line> findAllLinesByRepId(long id) {

        List<Line> allLines = new ArrayList<>();
        List<Long> linesId = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = dbConnect.con.prepareStatement("SELECT * FROM PUBLIC.REPORTS_LINES WHERE REPORT_ID = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                linesId.add(resultSet.getLong("ALLLINES_ID"));
            }

            for (long idTemp : linesId) {
                PreparedStatement preparedStatementLines = dbConnect.con.prepareStatement("SELECT * FROM PUBLIC.LINES WHERE LINES.ID = ?");
                preparedStatementLines.setLong(1, idTemp);
                resultSet = preparedStatementLines.executeQuery();

                while (resultSet.next()) {
                    Line line = new Line();
                    line.setId(resultSet.getLong("ID"));
                    line.setAverageWordLength(resultSet.getInt("AVERAGEWORDLENGTH"));
                    line.setFullLine(resultSet.getString("FULLLINE"));
                    line.setLineLength(resultSet.getInt("LINELENGTH"));
                    line.setLongestWord(resultSet.getString("LONGESTWORD"));
                    line.setShortestWord(resultSet.getString("SHORTESTWORD"));

                    allLines.add(line);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allLines;

    }

    public Line saveOrUpdate(Line line, long reportId) {
        if (line.getId() > 0) {
            update(line);
        } else {
            save(line, reportId);
        }
        return line;
    }

}
