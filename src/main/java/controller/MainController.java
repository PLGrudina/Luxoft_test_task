package controller;

import models.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.LineService;
import services.ReportService;

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
    public String getLibrary(Model model) {

        Report reportFirst = reportService.createFromUrl("Fate", "C:\\Users\\PavelGrudina\\IdeaProjects\\MyProject\\testtask\\src\\main\\webapp\\static\\text\\Fate.txt");
        Report reportSecond = reportService.createFromUrl("It’s not that I’m of God complaining", "C:\\Users\\PavelGrudina\\IdeaProjects\\MyProject\\testtask\\src\\main\\webapp\\static\\text\\It’s not that I’m of God complaining.txt");
        Report reportThird = reportService.createFromUrl("Young masters, if you only knew","C:\\Users\\PavelGrudina\\IdeaProjects\\MyProject\\testtask\\src\\main\\webapp\\static\\text\\Young masters, if you only knew.txt");

        List<Report> reportList = reportService.getAllReports();
        model.addAttribute("reportList", reportList);
        return "/library";
    }

    @RequestMapping(path = "/report", method = RequestMethod.GET)
    public String getReport(Model model) {
        return "/report";
    }
}
