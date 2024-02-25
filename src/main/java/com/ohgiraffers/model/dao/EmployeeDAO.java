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
            System.out.println("connenction");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String loginEmployee(Connection con, String empId, String empPwd) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String userPosition = null;
//        String isLoggedIn = null;
        String query = prop.getProperty("loginEmployee");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            pstmt.setString(2, empPwd);
//            pstmt.setString(3, userPosition);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                // 로그인 성공 시 사용자 권한 가져옴
                userPosition = rset.getString("position_code");
//                isLoggedIn = "로그인이 완료 되어습니다";
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

        return userPosition;
    }
    // 사용자 권한에 따라 처리하는 메서드
    public void processUserFunctionality(Connection con, String userposition) {
        switch (userposition) {
            case "ADMIN":
                // 관리자 권한 기능 실행
                 adminFunction(con);
                break;
            case "MGR":
                // 중간 관리자 권한 기능 실행
                 managerFunction(con);
                break;
            case "EMP":
                // 일반 사용자 권한 기능 실행
                 userFunction(con);
                break;
            default:
                System.out.println("알 수 없는 사용자입니다. ");
        }
    }

    private void adminFunction(Connection con) {
        // 관리자 기능 처리
    }

    private void managerFunction(Connection con) {
        // 중간 관리자 기능 처리
    }

    private void userFunction(Connection con) {
        //일반 사용자 기능 처리
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


