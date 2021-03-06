package com.pratik.corona.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pratik.corona.Model.CoronaData;
import com.pratik.corona.services.CoronaVirusServices;



@Controller
public class CoronaTrackerController {
	
	@Autowired
	CoronaVirusServices coronaVirusServices;
	CoronaData cdObj;
	
	@GetMapping("/")
	public String home(Model model) throws IOException 
	{
		List<CoronaData> locationdetails=coronaVirusServices.fetchvisusData();
		model.addAttribute("locationdetails", locationdetails);
		int totalReportedCases = locationdetails.stream().mapToInt(start -> start.getLatestTotalCases()).sum();
		int totalDeaths = locationdetails.stream().mapToInt(start -> start.getDeaths()).sum();
		int totalRecovered = locationdetails.stream().mapToInt(start -> start.getRecoverd()).sum();
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalDeaths", totalDeaths);
		model.addAttribute("totalRecovered", totalRecovered);
		
		//System.out.println(cdObj.getdate());
		
		return "home";
	}

}
