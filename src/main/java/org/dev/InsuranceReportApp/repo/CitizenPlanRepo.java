package org.dev.InsuranceReportApp.repo;

import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {

    @Query("Select distinct(c.planName) from CitizenPlan c ")
    List<String> getPlanNames();

    @Query("Select distinct(c.planStatus) from CitizenPlan c ")
    List<String> getPlanStatus();

    @Query("Select c from CitizenPlan c where " +
            "(:planName is null OR c.planName=:planName) AND" +
            "(:planStatus is null OR c.planStatus=:planStatus) AND" +
            "(:gender is null OR c.gender=:gender) AND" +
            "(:planStartDate is null OR c.planStartDate=:planStartDate) AND" +
            "(:planEndDate is null OR c.planEndDate=:planEndDate)")
    List<CitizenPlan> searchCitizens(@Param("planName") String planName, @Param("planStatus") String planStatus, @Param("gender") String gender, @Param("planStartDate") LocalDate planStartDate, @Param("planEndDate") LocalDate planEndDate);

    // , @Param("planStatus") String planStatus
}
