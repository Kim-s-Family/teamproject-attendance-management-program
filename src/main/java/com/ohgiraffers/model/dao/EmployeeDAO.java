package com.ohgiraffers.model.dao;


import com.ohgiraffers.model.dto.CreateEmployeeDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeDAO {


    private Properties prop = new Properties();

    public EmployeeDAO() {

        System.out.println("connection");
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            System.out.println("connenction2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String loginEmployee(Connection con, String empId, String empPwd) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String isLoggedIn = null;
        String query = prop.getProperty("loginEmployee");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            pstmt.setString(2, empPwd);

            rset = pstmt.executeQuery();
            if (rset.next()) {
                // If there's a result, login credentials are correct
                isLoggedIn = "로그인이 완료 되어습니다";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database login error", e);
        } finally {
            // Ensure resources are closed to prevent memory leaks
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isLoggedIn;
    }


    public Integer createEmployee(Connection con, CreateEmployeeDTO createEmployeeDTO) {
        PreparedStatement pstmt = null;
        Integer result = 0;
        String query = prop.getProperty("createEmployee");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, createEmployeeDTO.getEmpId());
            pstmt.setString(2, createEmployeeDTO.getEmpPwd());
            pstmt.setString(3, createEmployeeDTO.getEmpName());
            pstmt.setString(4, createEmployeeDTO.getPhone());
            pstmt.setString(5, createEmployeeDTO.getEmail());
            pstmt.setString(6, createEmployeeDTO.getDepartmentCode());
            pstmt.setString(7, createEmployeeDTO.getPositionCode());
            pstmt.setString(8, createEmployeeDTO.getEmpIdentification());
            pstmt.setString(9, createEmployeeDTO.getPaymentCode());
            int rowCount = pstmt.executeUpdate();
            if (rowCount > 0) {
                result = rowCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public List<EmployeeDTO> listEmployee(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;
        List<EmployeeDTO> employeeList = new ArrayList<>();
        String query = prop.getProperty("listEmployee");
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            while (rset.next()) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setEmpId(rset.getString("empId"));
                employee.setEmpPwd(rset.getString("empPassword"));
                employee.setEmpName(rset.getString("empName"));
                employee.setPhone(rset.getString("phone"));
                employee.setEmail(rset.getString("email"));
                employee.setDepartmentCode(rset.getString("departmentCode"));
                employee.setPositionCode(rset.getString("positionCode"));
                employee.setEmpIdentification(rset.getString("empIdentification"));
                employee.setPaymentCode(rset.getString("paymentCode"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 자원 해제
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employeeList;
    }
}


