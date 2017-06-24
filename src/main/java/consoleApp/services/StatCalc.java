package consoleApp.services;

import consoleApp.dao.AverageRapStatDaoImpl;
import consoleApp.dao.LineDaoConsoleImpl;
import consoleApp.dao.ReportDaoConsoleImpl;
import models.AverageRepStat;
import models.Line;
import models.Report;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
public class StatCalc {

    ReportDaoConsoleImpl reportDaoConsole = new ReportDaoConsoleImpl();
    LineDaoConsoleImpl lineDaoConsole = new LineDaoConsoleImpl();
    AverageRapStatDaoImpl averageRapStatDao = new AverageRapStatDaoImpl();

    public Report splitByLines(String name, String url) throws IOException {
        Report report = new Report();
        report.setName(name);
        report.setUrl(url);
        reportDaoConsole.save(report);

//        List<Line> allReportLinesList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(report.getUrl())));

        String str;

        while ((str = reader.readLine()) != null) {
            Line line = new Line();
            line.setFullLine(str);
            lineDaoConsole.save(line,report.getId());
//            allReportLinesList.add(line);
        }
        reader.close();

//        report.setAllLines(allReportLinesList);
//        reportDaoConsole.update(report);

        return report;
    }

    public AverageRepStat averageStat(long reportId) {
        Report report = reportDaoConsole.findById(reportId);
        AverageRepStat stat = new AverageRepStat();
        stat.setLinesCount(report.getAllLines().size());

        List<String> shortestWord = new ArrayList<>();
        List<String> longestWord = new ArrayList<>();
        List<Integer> averageWordLength = new ArrayList<>();

        for (Line line : report.getAllLines()) {
            shortestWord.add(line.getShortestWord());
            longestWord.add(line.getLongestWord());
            averageWordLength.add(line.getAverageWordLength());
        }

        int wordLength = 0;
        for (int word : averageWordLength) {
            wordLength = wordLength + word;
        }
        wordLength = wordLength / averageWordLength.size();
        stat.setAverageWordLength(wordLength);

        Collections.sort(shortestWord, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        Collections.sort(longestWord, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        stat.setShortestWord(shortestWord.get(0));
        stat.setLongestWord(longestWord.get(longestWord.size() - 1));

        averageRapStatDao.save(stat,reportId);

        report.setReportStatistics(stat);
        reportDaoConsole.update(report);

        return stat;
    }

    public void lineStatisticCalculate(List<Line> allReportLines) {
        for (Line line : allReportLines) {
            line.setLineLength(line.getFullLine().length());

            String[] tokens = line.getFullLine().split(" ");

            Arrays.sort(tokens, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            });

            line.setShortestWord(tokens[0]);
            line.setLongestWord(tokens[tokens.length - 1]);

            int wordsLength = 0;
            for (String word : tokens) {
                wordsLength = wordsLength + word.length();
            }
            wordsLength = Math.round(wordsLength / tokens.length);
            line.setAverageWordLength(wordsLength);

            lineDaoConsole.update(line);
        }
    }
}
