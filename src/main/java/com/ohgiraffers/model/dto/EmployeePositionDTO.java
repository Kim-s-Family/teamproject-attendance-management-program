package com.ohgiraffers.model.dto;

public class EmployeePositionDTO {
    private String positionName;
    private String positionCode;
    private String authorizationAccess;

    public EmployeePositionDTO(String positionName, String positionCode, String authorizationAccess) {
        this.positionName = positionName;
        this.positionCode = positionCode;
        this.authorizationAccess = authorizationAccess;
    }

    // Getters and setters
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getAuthorizationAccess() {
        return authorizationAccess;
    }

    public void setAuthorizationAccess(String authorizationAccess) {
        this.authorizationAccess = authorizationAccess;
    }
}
