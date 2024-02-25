package com.ohgiraffers.model.dao;


import com.ohgiraffers.model.dto.EmployeeDTO;
import com.ohgiraffers.model.dto.EmployeeDTO2;

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


    public void insertNewEmployee() {
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

        int result=insertNewEmployee(getConnection(),newEmployeeDTO);


        if (result > 0) {
            System.out.println("신규 직원 등록 성공!");
        } else {
            System.out.println("신규 직원 등록 실패!");
        }

    }


    public int updateEmpInfo(Connection con, EmployeeDTO changeInfo) {

        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("updateEmpInfo");
        EmployeeDTO changedInfo=new EmployeeDTO();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, changedInfo.getEmpId());
            pstmt.setString(2, changedInfo.getEmpPwd());
            pstmt.setString(3, changedInfo.getEmpName());
            pstmt.setString(4, changedInfo.getPhone());
            pstmt.setString(5, changedInfo.getEmail());
            pstmt.setString(6, changedInfo.getDepartmentCode());
            pstmt.setString(7, changedInfo.getPositionCode());
            pstmt.setString(8, changedInfo.getEmpIdentification());
            pstmt.setString(9, changedInfo.getPaymentCode());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }


    public void updateEmpInfo() {

        Scanner sc = new Scanner(System.in);

        System.out.print("변경할 직원ID를 입력하세요: ");
        String empId = sc.nextLine();
        System.out.print("변경할 PWD를 입력하세요: ");
        String empPWD = sc.nextLine();
        System.out.print("변경할 직원 이름을 입력하세요: ");
        String empName = sc.nextLine();
        System.out.print("변경할 연락처를 입력하세요: ");
        String phone = sc.nextLine();
        System.out.print("변경할 이메일을 입력하세요: ");
        String email = sc.nextLine();
        System.out.print("변경할 부서코드(DP1 ~ DP5) 중 하나를 입력하세요: ");
        String departmentCode = sc.nextLine();
        System.out.print("변경할 직책코드(PS1 ~ PS5) 중 하나를 입력하세요: ");
        String positionCode = sc.nextLine();
        System.out.print("변경할 사원분류(admin,mgr,emp) 중 하나를 입력해주세요: ");
        String empIdentification = sc.nextLine();
        System.out.print("변경할 급여코드(PM1 ~ PM5) 중 하나를 입력하세요: ");
        String paymentCode = sc.nextLine();


        EmployeeDTO changedInfo=new EmployeeDTO();
        changedInfo.setEmpId(empId);
        changedInfo.setEmpPwd(empPWD);
        changedInfo.setEmpName(empName);
        changedInfo.setPhone(phone);
        changedInfo.setEmail(email);
        changedInfo.setDepartmentCode(departmentCode);
        changedInfo.setPositionCode(positionCode);
        changedInfo.setEmpIdentification(empIdentification);
        changedInfo.setPaymentCode(paymentCode);


        int result=insertNewEmployee(getConnection(),changedInfo);
        if (result > 0) {
            System.out.println("변경 성공!");
        } else {
            System.out.println("변경 실패!");
        }
    }
    public int updateAttendanceRecord(Connection con, EmployeeDTO2 changeRecord) {

        PreparedStatement pstmt = null;
        System.out.println("changeRecord = " + changeRecord);

        int result = 0;

        String query = prop.getProperty("updateAttendanceRecord");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, changeRecord.getAttendanceCode());
            pstmt.setString(2, changeRecord.getEmpid());
            pstmt.setString(3, String.valueOf(changeRecord.getArrivalAtWork()));
            pstmt.setString(4, String.valueOf(changeRecord.getLeaveWork()));
            pstmt.setString(5, String.valueOf(changeRecord.getLateWork()));
            pstmt.setString(6, changeRecord.getVacation());
            pstmt.setString(7, String.valueOf(changeRecord.getDayOff()));

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;

    }

    public void updateAttendanceRecord() {

        EmployeeDTO2 changeRecord = new EmployeeDTO2();


        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 근태코드를 입력하세요: ");
        String attendanceCode = sc.nextLine();
        System.out.print("변경할 직원ID를 입력하세요: ");
        String empId = sc.nextLine();
        System.out.print("출근정보를 변경하시겠습니까?(Y/N) ");
        String arrivalAtWork = sc.nextLine();
        System.out.print("퇴근정보를 변경하시겠습니까?(Y/N) ");
        String leaveWork = sc.nextLine();
        System.out.print("지각정보를 변경하시겠습니까?(Y/N) ");
        String lateWork= sc.nextLine();
        System.out.print("휴가정보를 변경하시겠습니까?(Y/N) ");
        String vacation = sc.nextLine();
        System.out.print("결근정보를 변경하시겠습니까?(Y/N) ");
        String dayOff = sc.nextLine();
        int result=updateAttendanceRecord(getConnection(),changeRecord);

        if (result > 0) {
            System.out.println("변경 성공!");
        } else {
            System.out.println("변경 실패!");
        }
    }
}







