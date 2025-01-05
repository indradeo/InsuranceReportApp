package org.dev.InsuranceReportApp.service;

import jakarta.servlet.http.HttpServletResponse;
import org.dev.InsuranceReportApp.binder.SearchRequest;
import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.dev.InsuranceReportApp.repo.CitizenPlanRepo;
import org.dev.InsuranceReportApp.util.ExcelGenerator;
import org.dev.InsuranceReportApp.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CitizenPlanRepo repo;

    @Autowired
    private ExcelGenerator excelGenerator;

    @Autowired
    private PdfGenerator pdfGenerator;

    private List<CitizenPlan> citizens;

    @Override
    public List<String> getPlanNames() {
        return repo.getPlanNames();
    }

    @Override
    public List<String> getPlanStatus() {
        return repo.getPlanStatus();
    }


    @Override
    public List<CitizenPlan> searchCitizens(SearchRequest req) {

        CitizenPlan entity = new CitizenPlan();

        if (null != req.getPlanName() && !"".equals(req.getPlanName())) {
            entity.setPlanName(req.getPlanName());
        }
        if (null != req.getPlanStatus() && !"".equals(req.getPlanStatus())) {
            entity.setPlanStatus(req.getPlanStatus());
        }
        if (null != req.getGender() && !"".equals(req.getGender())) {
            entity.setGender(req.getGender());
        }

        if (null != req.getPlanStartDate()) {
            entity.setPlanStartDate(req.getPlanStartDate());
        }

        if (null != req.getPlanEndDate()) {
            entity.setPlanEndDate(req.getPlanEndDate());
        }
        citizens=repo.findAll(Example.of(entity));
        return citizens;
    }


    @Override
    public boolean exportExcel(HttpServletResponse response) throws Exception {

        excelGenerator.generateExcel(response,citizens);

        return true;
    }

    @Override
    public boolean exportPdf(HttpServletResponse response) throws Exception {

        pdfGenerator.generatePdf(response,citizens);

        return false;
    }

    @Override
    public List<CitizenPlan> findAll() {

        return repo.findAll();
    }
}
