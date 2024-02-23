package com.ohgiraffers.model.dao;


import com.ohgiraffers.model.dto.EmployeeDTO;

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

    public int insertNewMenu(Connection con, EmployeeDTO newEmployee) {

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
            pstmt.setString(6, newEmployee.getEmail());
            pstmt.setString(7, newEmployee.getDepartmentCode());
            pstmt.setString(8, newEmployee.getPositionCode());
            pstmt.setString(9, newEmployee.getEmpIdentification());
            pstmt.setString(10, newEmployee.getPaymentCode());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;


    }







}


