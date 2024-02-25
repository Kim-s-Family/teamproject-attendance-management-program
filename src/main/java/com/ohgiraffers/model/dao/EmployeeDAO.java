package com.ohgiraffers.model.dao;


import com.ohgiraffers.model.dto.EmployeeDTO;
import com.ohgiraffers.model.dto.EmployeeDTO2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

/*
    3. 정보입력 CREATE
① admin은 emp_info Table 정보 입력 가능 (메서드 insertNewEmployee)
    4. 정보수정 UPDATE
① admin은 emp_info Table 수정 가능(메서드 updateEmpInfo)
    attendance_record 수정 가능 (메서드 updateEmpAttendance)
    */


public class EmployeeDAO {

    private Properties prop = new Properties();

    public EmployeeDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertNewEmployee(Connection con, EmployeeDTO newEmployee) {

        PreparedStatement pstmt = null;
        System.out.println("newEmployee = " + newEmployee);

        int result = 0;

        String query = prop.getProperty("insertNewEmployee");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newEmployee.getEmpId());
            pstmt.setString(2, newEmployee.getEmpPwd());
            pstmt.setString(3, newEmployee.getEmpName());
            pstmt.setString(4, newEmployee.getPhone());
            pstmt.setString(5, newEmployee.getEmail());
            pstmt.setString(6, newEmployee.getDepartmentCode());
            pstmt.setString(7, newEmployee.getPositionCode());
            pstmt.setString(8, newEmployee.getEmpIdentification());
            pstmt.setString(9, newEmployee.getPaymentCode());




            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

//    public int updateEmpInfo(Connection con, EmployeeDTO changeInfo) {
//
//        PreparedStatement pstmt = null;
//        System.out.println("changeInfo = " + changeInfo);
//
//        int result = 0;
//
//        String query = prop.getProperty("updateEmpInfo");
//
//        try {
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, changeInfo.getEmpId());
//            pstmt.setString(2, changeInfo.getEmpPwd());
//            pstmt.setString(3, changeInfo.getEmpName());
//            pstmt.setString(4, changeInfo.getPhone());
//            pstmt.setString(5, changeInfo.getEmail());
//            pstmt.setString(6, changeInfo.getDepartmentCode());
//            pstmt.setString(7, changeInfo.getPositionCode());
//            pstmt.setString(8, changeInfo.getEmpIdentification());
//            pstmt.setString(9, changeInfo.getPaymentCode());
//
//            result = pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            close(pstmt);
//        }
//
//        return result;
//
//    }
//
//    public int updateAttendanceRecord(Connection con, EmployeeDTO2 changeRecord) {
//
//        PreparedStatement pstmt = null;
//        System.out.println("changeRecord = " + changeRecord);
//
//        int result = 0;
//
//        String query = prop.getProperty("updateAttendanceRecord");
//
//        try {
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, changeRecord.getAttendanceCode());
//            pstmt.setString(2, changeRecord.getEmpid());
//            pstmt.setString(3, String.valueOf(changeRecord.getArrivalAtWork()));
//            pstmt.setString(4, String.valueOf(changeRecord.getLeaveWork()));
//            pstmt.setString(5, String.valueOf(changeRecord.getLateWork()));
//            pstmt.setString(6, changeRecord.getVacation());
//            pstmt.setString(7, String.valueOf(changeRecord.getDayOff()));
//
//            result = pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            close(pstmt);
//        }
//
//        return result;
//
//    }
}


