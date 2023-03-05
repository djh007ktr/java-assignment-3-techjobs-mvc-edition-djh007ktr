package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            ArrayList<Job> jobs = JobData.findAll();
            searchType = "all";
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchType", searchType);
        } else {
            ArrayList<Job> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);
            model.addAttribute("searchType", searchType);
        }
        return "search";
    }
}
