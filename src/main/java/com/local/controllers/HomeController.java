package com.local.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.local.models.LocationStats;
import com.local.services.CoronaDataService;

@Controller
public class HomeController {

	@Autowired
	CoronaDataService coronaDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaDataService.getAllStats();
		Integer totalCases = allStats.stream().mapToInt(oneStat -> oneStat.getLatestTotalCases()).sum();
		Integer previousTotalCases = allStats.stream().mapToInt(oneStat -> oneStat.getPreviousDayTotalCases()).sum();
		int totalNewCase = totalCases-previousTotalCases;
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalCases);
		model.addAttribute("totalNewCase", totalNewCase);
		return "home";
	}
}
