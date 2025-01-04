package org.dev.InsuranceReportApp.controller;

import jakarta.servlet.http.HttpServletResponse;
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
		return "index";
	}

	@PostMapping("/search")
	public String searchCitizen(SearchRequest req, Model model) {
		System.out.println(req);
		init(model);
		List<CitizenPlan> citizens = reportService.searchCitizens(req);
		System.out.println(citizens);
		model.addAttribute("citizens", citizens);
		model.addAttribute("searchRequest" , req);

		return "index";
	}

	@GetMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition","attachment; filename=plans.xls");
		reportService.exportExcel(response);
	}

	@GetMapping("/exportPdf")
	public void exportPdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition","attachment; filename=plans.pdf");
		reportService.exportPdf(response);
	}










	@GetMapping("/searchC")
	@ResponseBody
	public List<CitizenPlan> search(@ModelAttribute SearchRequest req) {

		List<CitizenPlan> citi = reportService.searchCitizens(req);
		System.out.println(citi);
		return citi;
	}

	private void init(Model model) {
		
		model.addAttribute("planNames", reportService.getPlanNames());
		model.addAttribute("planStatuses", reportService.getPlanStatus());
	}

}
