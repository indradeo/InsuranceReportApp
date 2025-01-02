package org.dev.InsuranceReportApp.service;

import org.dev.InsuranceReportApp.binder.SearchRequest;
import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {


    List<String> getPlanNames();

    List<String> getPlanStatus();

    List<CitizenPlan> searchCitizens(SearchRequest req);

    boolean exportExcel();

    boolean exportPdf();

	List<CitizenPlan> findAll();
}
