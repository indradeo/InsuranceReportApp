package org.dev.InsuranceReportApp.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.dev.InsuranceReportApp.binder.MailDetails;
import org.dev.InsuranceReportApp.binder.SearchRequest;
import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.dev.InsuranceReportApp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/welcome")
	public String welcome(Model model) {
		List<CitizenPlan> citizens=null;
		model.addAttribute("citizens", citizens);
		init(model);
		model.addAttribute("searchRequest", new SearchRequest());
		model.addAttribute("isData",false);
		return "index";
	}

	@PostMapping("/search")
	public String searchCitizen(SearchRequest req, Model model) {
		init(model);
		List<CitizenPlan> citizens = reportService.searchCitizens(req);
		System.out.println(citizens);
		model.addAttribute("citizens", citizens);
		model.addAttribute("searchRequest" , req);

		return "index";
	}

	@GetMapping("/exportExcel")
	public String exportExcel(HttpServletResponse response, MailDetails mailDetails, Model model) throws Exception {
		//response.setContentType("application/octet-stream");
		//response.addHeader("Content-Disposition","attachment; filename=plans.xls");
		reportService.exportExcel(response, mailDetails);
		init(model);
		return "redirect:/welcome";
	}

	@GetMapping("/exportPdf")
	public String exportPdf(HttpServletResponse response, MailDetails mailDetails, Model model) throws Exception {
		//response.setContentType("application/pdf");
		//response.addHeader("Content-Disposition","attachment; filename=plans.pdf");
		reportService.exportPdf(response, mailDetails);
		init(model);

		return "redirect:/welcome";
	}


	private void init(Model model) {
		
		model.addAttribute("planNames", reportService.getPlanNames());
		model.addAttribute("planStatuses", reportService.getPlanStatus());
		model.addAttribute("mailDetails", new MailDetails());
		model.addAttribute("isData",true);
	}

}
