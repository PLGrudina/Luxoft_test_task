package services.Impl;

import dao.AverageRepStatDao;
import dao.LineDao;
import dao.ReportDao;
import models.AverageRepStat;
import models.Line;
import models.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.ReportService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by PavelGrudina on 17.06.2017.
 */
@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    @Autowired
    LineDao lineDao;

    @Autowired
    ReportDao reportDao;

    @Autowired
    AverageRepStatDao averageRepStatDao;

    @Override
    @Transactional
    public Report save(Report report) {
        reportDao.saveOrUpdate(report);
        return report;
    }

    @Override
    @Transactional
    public void delete(long id) {
        reportDao.delete(id);
    }

    @Override
    @Transactional
    public Report splitByLines(String name, String url) throws IOException {
        Report report = new Report();
        report.setName(name);
        report.setUrl(url);

        Line line = new Line();

        List<Line> allReportLinesList = new ArrayList<>();


        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(report.getUrl())));

        String str;

        while ((str = reader.readLine()) != null) {
            line.setFullLine(str);
            lineDao.save(line);
            allReportLinesList.add(line);
        }
        reader.close();

        report.setAllLines(allReportLinesList);
        reportDao.save(report);

        return report;
    }

    @Override
    @Transactional
    public AverageRepStat averageStat(long reportId) {
        Report report = reportDao.findById(reportId);
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
            wordLength = +word;
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
        stat.setLongestWord(longestWord.get(longestWord.size()-1));

        averageRepStatDao.save(stat);

        return stat;
    }
}
