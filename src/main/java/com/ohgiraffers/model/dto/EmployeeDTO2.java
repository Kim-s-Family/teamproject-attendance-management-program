package com.ohgiraffers.model.dto;

import java.io.Serializable;

public class EmployeeDTO2 implements Serializable {


    private String attendanceCode;
    private String empId;
    private Character arrivalAtWork;
    private Character leaveWork;
    private Character lateWork;
    private String vacation;
    private Character dayOff;

    public EmployeeDTO2() {}

    public EmployeeDTO2(String attendanceCode, String emp_id, char arrivalAtWork, char leaveWork, char lateWork, String vacation, char dayOff) {
        this.attendanceCode = attendanceCode;
        this.empId = emp_id;
        this.arrivalAtWork = arrivalAtWork;
        this.leaveWork = leaveWork;
        this.lateWork = lateWork;
        this.vacation = vacation;
        this.dayOff = dayOff;
    }

    public String getAttendanceCode() {
        return attendanceCode;
    }

    public void setAttendanceCode(String attendanceCode) {
        this.attendanceCode = attendanceCode;
    }

    public String getEmpid() {
        return empId;
    }

    public void setEmp_id(String emp_id) {
        this.empId = emp_id;
    }

    public char getArrivalAtWork() {
        return arrivalAtWork;
    }

    public void setArrivalAtWork(char arrivalAtWork) {
        this.arrivalAtWork = arrivalAtWork;
    }

    public char getLeaveWork() {
        return leaveWork;
    }

    public void setLeaveWork(char leaveWork) {
        this.leaveWork = leaveWork;
    }

    public char getLateWork() {
        return lateWork;
    }

    public void setLateWork(char lateWork) {
        this.lateWork = lateWork;
    }

    public String getVacation() {
        return vacation;
    }

    public void setVacation(String vacation) {
        this.vacation = vacation;
    }

    public char getDayOff() {
        return dayOff;
    }

    public void setDayOff(char dayOff) {
        this.dayOff = dayOff;
    }

    @Override
    public String toString() {
        return "EmployeeDTO2{" +
                "attendanceCode='" + attendanceCode + '\'' +
                ", emp_id='" + empId + '\'' +
                ", arrivalAtWork=" + arrivalAtWork +
                ", leaveWork=" + leaveWork +
                ", lateWork=" + lateWork +
                ", vacation='" + vacation + '\'' +
                ", dayOff=" + dayOff +
                '}';
    }
}
