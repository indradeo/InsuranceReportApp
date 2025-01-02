package org.dev.InsuranceReportApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@Entity
@Table(name = "CITIZEN_PLAN_INFO")
public class CitizenPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citizenId;
    private String citizenName;
    private String gender;
    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Double benefitAmount;
    private String denialReason;
    private LocalDate terminatedDate;
    private String terminationReason;

}

