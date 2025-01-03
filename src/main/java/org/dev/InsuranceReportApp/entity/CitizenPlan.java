package org.dev.InsuranceReportApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;


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

    public Double getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Double benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Integer getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Integer citizenId) {
        this.citizenId = citizenId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getDenialReason() {
        return denialReason;
    }

    public void setDenialReason(String denialReason) {
        this.denialReason = denialReason;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(LocalDate planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public LocalDate getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public LocalDate getTerminatedDate() {
        return terminatedDate;
    }

    public void setTerminatedDate(LocalDate terminatedDate) {
        this.terminatedDate = terminatedDate;
    }

    public String getTerminationReason() {
        return terminationReason;
    }

    public void setTerminationReason(String terminationReason) {
        this.terminationReason = terminationReason;
    }

    @Override
    public String toString() {
        return "CitizenPlan{" +
                "benefitAmount=" + benefitAmount +
                ", citizenId=" + citizenId +
                ", citizenName='" + citizenName + '\'' +
                ", gender='" + gender + '\'' +
                ", planName='" + planName + '\'' +
                ", planStatus='" + planStatus + '\'' +
                ", planStartDate=" + planStartDate +
                ", planEndDate=" + planEndDate +
                ", denialReason='" + denialReason + '\'' +
                ", terminatedDate=" + terminatedDate +
                ", terminationReason='" + terminationReason + '\'' +
                '}';
    }
}

