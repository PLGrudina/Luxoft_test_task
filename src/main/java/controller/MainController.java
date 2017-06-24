package controller;

import models.Line;
import models.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.LineService;
import services.ReportService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PavelGrudina on 20.06.2017.
 */
@Controller
public class MainController {

    @Autowired
    LineService lineService;

    @Autowired
    ReportService reportService;

    @RequestMapping(path = "/library", method = RequestMethod.GET)
    public String getLibrary(@RequestParam(name = "filter", required = false) String value, Model model) {

        if (reportService.getAllReports().size() == 0) {
            Report reportFirst = reportService.createFromUrl("Fate", "C:\\Users\\PavelGrudina\\IdeaProjects\\MyProject\\testtask\\src\\main\\webapp\\static\\text\\Fate.txt");
            Report reportSecond = reportService.createFromUrl("It’s not that I’m of God complaining", "C:\\Users\\PavelGrudina\\IdeaProjects\\MyProject\\testtask\\src\\main\\webapp\\static\\text\\It’s not that I’m of God complaining.txt");
            Report reportThird = reportService.createFromUrl("Young masters, if you only knew", "C:\\Users\\PavelGrudina\\IdeaProjects\\MyProject\\testtask\\src\\main\\webapp\\static\\text\\Young masters, if you only knew.txt");
        }

        List<Report> reportsAll = reportService.getAllReports();
        List<Report> reportList = new ArrayList<>();

        if (value != null) {
            for (Report report : reportsAll) {
                if (report.getAllLines().size() > Integer.parseInt(value)) {
                    reportList.add(report);
                }
            }
            model.addAttribute("reportList", reportList);
        } else {
            model.addAttribute("reportList", reportsAll);
        }

        return "/library";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String textHandler(Model model) {
        return "/edit";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String saveText(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "text", required = false) String text) {
        List<Line> allReportLinesList = new ArrayList<>();

        Report report = new Report();
        report.setName(name);
        reportService.save(report);
        String[] lineList = text.split("\n");

        for (String str : lineList) {
            Line line = new Line();
            line.setFullLine(str);
            lineService.save(line);
            allReportLinesList.add(line);
        }
        report.setAllLines(allReportLinesList);
        reportService.save(report);

        lineService.lineStatisticCalculate(report.getAllLines());
        reportService.averageStat(report.getId());

        return "redirect:/library";
    }

    @RequestMapping(path = "/report", method = RequestMethod.GET)
    public String getReport(Model model) {
        return "/report";
    }
}
