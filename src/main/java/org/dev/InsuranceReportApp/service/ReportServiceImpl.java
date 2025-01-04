package org.dev.InsuranceReportApp.service;


import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dev.InsuranceReportApp.binder.SearchRequest;
import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.dev.InsuranceReportApp.repo.CitizenPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CitizenPlanRepo repo;

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

        return repo.findAll(Example.of(entity));
    }


    @Override
    public boolean exportExcel(HttpServletResponse response) throws Exception {

        List<CitizenPlan> citizenPlans = repo.findAll();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("PlansData");
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("S.No");
        headerRow.createCell(1).setCellValue("Citizen Id");
        headerRow.createCell(2).setCellValue("Citizen Name");
        headerRow.createCell(3).setCellValue("Plan Name");
        headerRow.createCell(4).setCellValue("Plan Status");
        headerRow.createCell(5).setCellValue("Start Date");
        headerRow.createCell(6).setCellValue("End Date");
        headerRow.createCell(7).setCellValue("Gender");
        headerRow.createCell(8).setCellValue("Termination Date");
        headerRow.createCell(9).setCellValue("Termination Reason");
        headerRow.createCell(10).setCellValue("Denial Reason");
        headerRow.createCell(11).setCellValue("Benefit Amount");
        int rowNumber = 1;
        for (CitizenPlan plan : citizenPlans) {
            Row row = sheet.createRow(rowNumber);

            row.createCell(0).setCellValue(rowNumber);
            row.createCell(1).setCellValue(plan.getCitizenId());
            row.createCell(2).setCellValue(plan.getCitizenName());
            row.createCell(3).setCellValue(plan.getPlanName());
            row.createCell(4).setCellValue(plan.getPlanStatus());
            row.createCell(5).setCellValue(plan.getPlanStartDate()+"");
            row.createCell(6).setCellValue(plan.getPlanEndDate()+"");
            row.createCell(7).setCellValue(plan.getGender());
            row.createCell(8).setCellValue(plan.getTerminatedDate()+"");
            row.createCell(9).setCellValue(plan.getTerminationReason());
            row.createCell(10).setCellValue(plan.getDenialReason());
            if(null!=plan.getBenefitAmount()){
                row.createCell(11).setCellValue(plan.getBenefitAmount());
            }else{
                row.createCell(11).setCellValue("0.00");
            }


            rowNumber++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();

        return true;
    }

    @Override
    public boolean exportPdf(HttpServletResponse response) throws Exception {

        Document document= new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();

        document.add(new Paragraph("Citizen Plan Info"));
        document.close();


        //half completed

        return false;
    }

    @Override
    public List<CitizenPlan> findAll() {

        return repo.findAll();
    }
}
