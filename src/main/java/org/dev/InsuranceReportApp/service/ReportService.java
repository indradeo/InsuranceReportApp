package org.dev.InsuranceReportApp.service;

import jakarta.servlet.http.HttpServletResponse;
import org.dev.InsuranceReportApp.binder.MailDetails;
import org.dev.InsuranceReportApp.binder.SearchRequest;
import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {


    List<String> getPlanNames();

    List<String> getPlanStatus();

    List<CitizenPlan> searchCitizens(SearchRequest req);

    boolean exportExcel(HttpServletResponse response, MailDetails mailDetails) throws Exception;

    boolean exportPdf(HttpServletResponse response, MailDetails mailDetails) throws Exception;

	List<CitizenPlan> findAll();
}
