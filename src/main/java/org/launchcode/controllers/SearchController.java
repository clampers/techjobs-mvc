package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    public String processSearch(Model model,
                         @RequestParam String searchType, String searchTerm) {

        model.addAttribute("columns", ListController.columnChoices);

        ArrayList<HashMap<String, String>> searchResults;

        if(searchType.equals("all")) {
            searchResults = JobData.findByValue(searchTerm);

        } else {
            searchResults = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        if(searchResults.isEmpty()) {
            model.addAttribute("noEntry", "No entries found.");
        } else {
            model.addAttribute("numberOfResults", searchResults.size() + " entries found.");
        }

        model.addAttribute("searchResults", searchResults);

        return "search";
    }

}
