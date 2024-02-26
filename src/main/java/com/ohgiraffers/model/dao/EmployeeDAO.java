package com.ohgiraffers.model.dao;


import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

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

    public int insertNewEmployee() {

        Connection con = getConnection();
        Scanner sc = new Scanner(System.in);

        System.out.print("등록할 직원ID를 입력하세요: ");
        String empId = sc.nextLine();
        System.out.print("등록할 직원PWD를 입력하세요: ");
        String empPWD = sc.nextLine();
        System.out.print("등록할 직원 이름을 입력하세요: ");
        String empName = sc.nextLine();
        System.out.print("등록할 연락처를 입력하세요: ");
        String phone = sc.nextLine();
        System.out.print("등록할 이메일을 입력하세요: ");
        String email = sc.nextLine();
        System.out.print("등록할 부서코드(DP1 ~ DP5) 중 하나를 입력하세요: ");
        String departmentCode = sc.nextLine();
        System.out.print("등록할 직책코드(PS1 ~ PS5) 중 하나를 입력하세요: ");
        String positionCode = sc.nextLine();
        System.out.print("사원분류(admin,mgr,emp) 중 하나를 입력해주세요: ");
        String empIdentification = sc.nextLine();
        System.out.print("등록할 급여코드(PM1 ~ PM5) 중 하나를 입력하세요: ");
        String paymentCode = sc.nextLine();

        EmployeeDTO newEmployeeDTO = new EmployeeDTO(empId, empPWD, empName, phone, email, departmentCode, positionCode, empIdentification, paymentCode);
        EmployeeDTO newEmployee = newEmployeeDTO;

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

            if (result > 0) {
                System.out.println("신규 직원 등록 성공!");
            } else {
                System.out.println("신규 직원 등록 실패!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;

    }
}














