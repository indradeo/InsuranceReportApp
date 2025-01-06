package org.dev.InsuranceReportApp.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class PdfGenerator {

    public void generatePdf(HttpServletResponse response, List<CitizenPlan> citizens, File file) throws Exception {

        Document document= new Document(PageSize.A4);

       // PdfWriter.getInstance(document,response.getOutputStream());
        PdfWriter.getInstance(document,new FileOutputStream(file));
        document.open();

        Paragraph p=new Paragraph("Citizen Plan Info");
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN));
        document.add(p);

        PdfPTable table= new PdfPTable(6);

        String columnNames[]={"S.No", "Citizen Name","Plan Name","Plan Status","Gender", "Benefit Amt"};
        for(String cell:columnNames){
            table.addCell(cell);
        }

        int serialNumber=1;
        for(CitizenPlan citizen:citizens){
            table.addCell(String.valueOf(serialNumber));
            table.addCell(citizen.getCitizenName());
            table.addCell(citizen.getPlanName());
            table.addCell(citizen.getPlanStatus());
            table.addCell(citizen.getGender());
            if(citizen.getBenefitAmount()!=null){
                table.addCell(String.valueOf(citizen.getBenefitAmount()));
            }else{
                table.addCell("N/A");
            }
            serialNumber++;
        }
        document.add(table);

        document.close();



    }
}
