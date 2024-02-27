package com.ohgiraffers.model.dto;

public class EmployeeCompensationDTO {
    private String empId;
    private String monthCode;
    private String compensationStatus;
    private String penaltyStatus;
    private String penaltyScoreSum;

    public EmployeeCompensationDTO(String empId, String monthCode, String compensationStatus, String penaltyStatus, String penaltyScoreSum) {
        this.empId = empId;
        this.monthCode = monthCode;
        this.compensationStatus = compensationStatus;
        this.penaltyStatus = penaltyStatus;
        this.penaltyScoreSum = penaltyScoreSum;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMonthCode() {
        return monthCode;
    }

    public void setMonthCode(String monthCode) {
        this.monthCode = monthCode;
    }

    public String getCompensationStatus() {
        return compensationStatus;
    }

    public void setCompensationStatus(String compensationStatus) {
        this.compensationStatus = compensationStatus;
    }

    public String getPenaltyStatus() {
        return penaltyStatus;
    }

    public void setPenaltyStatus(String penaltyStatus) {
        this.penaltyStatus = penaltyStatus;
    }

    public String getPenaltyScoreSum() {
        return penaltyScoreSum;
    }

    public void setPenaltyScoreSum(String penaltyScoreSum) {
        this.penaltyScoreSum = penaltyScoreSum;
    }

    @Override
    public String toString() {
        return "CompensationDTO{" +
                "empId='" + empId + '\'' +
                ", monthCode='" + monthCode + '\'' +
                ", compensationStatus='" + compensationStatus + '\'' +
                ", penaltyStatus='" + penaltyStatus + '\'' +
                ", penaltyScoreSum='" + penaltyScoreSum + '\'' +
                '}';
    }
}