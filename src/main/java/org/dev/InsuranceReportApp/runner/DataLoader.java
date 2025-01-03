package org.dev.InsuranceReportApp.runner;

import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.dev.InsuranceReportApp.repo.CitizenPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDate;
import java.util.Arrays;


public class DataLoader implements ApplicationRunner {
    @Autowired
    private CitizenPlanRepo repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //cash plan data
        CitizenPlan c1=new CitizenPlan();
        c1.setCitizenName("John");
        c1.setGender("Male");
        c1.setPlanName("Cash");
        c1.setPlanStatus("Approved");
        c1.setPlanStartDate(LocalDate.now());
        c1.setPlanEndDate(LocalDate.now().plusMonths(6));
        c1.setBenefitAmount(5000.00);

        CitizenPlan c2=new CitizenPlan();
        c2.setCitizenName("Smith");
        c2.setGender("Male");
        c2.setPlanName("Cash");
        c2.setPlanStatus("Denied");
        c2.setDenialReason("Rental Income");

        CitizenPlan c3=new CitizenPlan();
        c3.setCitizenName("Ketty");
        c3.setGender("Female");
        c3.setPlanName("Cash");
        c3.setPlanStatus("Terminated");
        c3.setPlanStartDate(LocalDate.now().minusMonths(4));
        c3.setPlanEndDate(LocalDate.now().plusMonths(6));
        c3.setBenefitAmount(5000.00);
        c3.setTerminatedDate(LocalDate.now());
        c3.setTerminationReason("Employed");

        //Food plan data
        CitizenPlan c4=new CitizenPlan();
        c4.setCitizenName("David");
        c4.setGender("Male");
        c4.setPlanName("Food");
        c4.setPlanStatus("Approved");
        c4.setPlanStartDate(LocalDate.now());
        c4.setPlanEndDate(LocalDate.now().plusMonths(6));
        c4.setBenefitAmount(4000.00);

        CitizenPlan c5=new CitizenPlan();
        c5.setCitizenName("Robert");
        c5.setGender("Male");
        c5.setPlanName("Food");
        c5.setPlanStatus("Denied");
        c5.setDenialReason("Property Income");

        CitizenPlan c6=new CitizenPlan();
        c6.setCitizenName("Evelin");
        c6.setGender("Female");
        c6.setPlanName("Food");
        c6.setPlanStatus("Terminated");
        c6.setPlanStartDate(LocalDate.now().minusMonths(4));
        c6.setPlanEndDate(LocalDate.now().plusMonths(6));
        c6.setBenefitAmount(5000.00);
        c6.setTerminatedDate(LocalDate.now());


        //Medical plan data
        CitizenPlan c7=new CitizenPlan();
        c7.setCitizenName("Dev");
        c7.setGender("Male");
        c7.setPlanName("Medical");
        c7.setPlanStatus("Approved");
        c7.setPlanStartDate(LocalDate.now());
        c7.setPlanEndDate(LocalDate.now().plusMonths(6));
        c7.setBenefitAmount(15000.00);

        CitizenPlan c8=new CitizenPlan();
        c8.setCitizenName("Guru");
        c8.setGender("Male");
        c8.setPlanName("Medical");
        c8.setPlanStatus("Denied");
        c8.setDenialReason("Tax-payer");

        CitizenPlan c9=new CitizenPlan();
        c9.setCitizenName("Siri");
        c9.setGender("Female");
        c9.setPlanName("Medical");
        c9.setPlanStatus("Terminated");
        c9.setPlanStartDate(LocalDate.now().minusMonths(4));
        c9.setPlanEndDate(LocalDate.now().plusMonths(6));
        c9.setBenefitAmount(15000.00);
        c9.setTerminatedDate(LocalDate.now());
        c9.setTerminationReason("Govt Employed");

        //Employment plan data
        CitizenPlan c10=new CitizenPlan();
        c10.setCitizenName("Kalpesh");
        c10.setGender("Male");
        c10.setPlanName("Employment");
        c10.setPlanStatus("Approved");
        c10.setPlanStartDate(LocalDate.now());
        c10.setPlanEndDate(LocalDate.now().plusMonths(6));
        c10.setBenefitAmount(5000.00);

        CitizenPlan c11=new CitizenPlan();
        c11.setCitizenName("Jignesh");
        c11.setGender("Male");
        c11.setPlanName("Employment");
        c11.setPlanStatus("Denied");
        c11.setDenialReason("Rental Income");

        CitizenPlan c12=new CitizenPlan();
        c12.setGender("Female");
        c12.setCitizenName("Peri");
        c12.setPlanName("Employment");
        c12.setPlanStatus("Terminated");
        c12.setPlanStartDate(LocalDate.now().minusMonths(4));
        c12.setPlanEndDate(LocalDate.now().plusMonths(6));
        c12.setBenefitAmount(5000.00);
        c12.setTerminatedDate(LocalDate.now());
        c12.setTerminationReason("Employed");

       repo.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12));
    }
}
