package services.Impl;

import dao.LineDao;
import models.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.LineService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by PavelGrudina on 17.06.2017.
 */
@Service
@Transactional(readOnly = true)
public class LineServiceImpl implements LineService {

    @Autowired
    LineDao lineDao;

    @Override
    @Transactional
    public Line save(Line line) {
        lineDao.saveOrUpdate(line);
        return line;
    }

    @Override
    @Transactional
    public void delete(long id) {
        lineDao.delete(id);
    }

    @Override
    @Transactional
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
                wordsLength = +word.length();
            }
            wordsLength = Math.round(wordsLength / tokens.length);
            line.setAverageWordLength(wordsLength);

            lineDao.save(line);
        }
    }
}
