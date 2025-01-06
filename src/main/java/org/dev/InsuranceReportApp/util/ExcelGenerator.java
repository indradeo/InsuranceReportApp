package org.dev.InsuranceReportApp.util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.dev.InsuranceReportApp.repo.CitizenPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class ExcelGenerator {

    @Autowired
    private CitizenPlanRepo repo;

    public void generateExcel(HttpServletResponse response, List<CitizenPlan> citizenPlans, File file) throws Exception {

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

        FileOutputStream fos= new FileOutputStream(file);
        workbook.write(fos);

        /*ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();*/
        workbook.close();

    }
}
