package com.ohgiraffers.test;

import com.ohgiraffers.model.dao.EmployeeDAO;
import com.ohgiraffers.model.dto.EmployeeDTO;
import com.ohgiraffers.model.dto.EmployeeDTO2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class TestApplication {

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        /*정보입력 insertNewEmployee*/
        EmployeeDAO registerDAO = new EmployeeDAO();
        EmployeeDTO newEmployee = new EmployeeDTO();

        int result = registerDAO.insertNewEmployee(con, newEmployee);


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

        System.out.println("Employee ID: "+ empId);
        System.out.println("emp PWD: "+ empPWD);
        System.out.println("phone: "+empName);
        System.out.println("email: "+phone);
        System.out.println("departmentCode: "+email);
        System.out.println("positionCode: "+positionCode);
        System.out.println("empIdentification: "+empIdentification);
        System.out.println("paymentCode: "+ paymentCode);
        if (result > 0) {
            System.out.println("신규 직원 등록 성공!");
        } else {
            System.out.println("신규 직원 등록 실패!");
        }

//        /*정보수정 changeEmpInfo*/
//
//        EmployeeDAO changeDAO = new EmployeeDAO();
//        EmployeeDTO changeInfo = new EmployeeDTO();
//
//        result = changeDAO.updateEmpInfo(con, changeInfo);
//
//
//        System.out.print("변경할 직원ID를 입력하세요: ");
//        empId = sc.nextLine();
//        System.out.print("변경할 직원PWD를 입력하세요: ");
//        empPWD = sc.nextLine();
//        System.out.print("변경할 직원 이름을 입력하세요: ");
//        empName = sc.nextLine();
//        System.out.print("변경할 연락처를 입력하세요: ");
//        phone = sc.nextLine();
//        System.out.print("변경할 이메일을 입력하세요: ");
//        email = sc.nextLine();
//        System.out.print("변경할 부서코드(DP1 ~ DP5) 중 하나를 입력하세요: ");
//        departmentCode = sc.nextLine();
//        System.out.print("변경할 직책코드(PS1 ~ PS5) 중 하나를 입력하세요: ");
//        positionCode = sc.nextLine();
//        System.out.print("변경할 사원분류(admin,mgr,emp) 중 하나를 입력해주세요: ");
//        empIdentification = sc.nextLine();
//        System.out.print("변경할 급여코드(PM1 ~ PM5) 중 하나를 입력하세요: ");
//        paymentCode = sc.nextLine();
//
//        if (result > 0) {
//            System.out.println("변경 성공!");
//        } else {
//            System.out.println("변경 실패!");
//        }
//
//        /*정보수정 changeAttendanceRecord*/
//
//        EmployeeDAO updateDAO = new EmployeeDAO();
//        EmployeeDTO2 changeRecord = new EmployeeDTO2();
//
//        result = updateDAO.updateAttendanceRecord(con, changeRecord);
//
//        System.out.print("변경할 근태코드를 입력하세요: ");
//        String attendanceCode = sc.nextLine();
//        System.out.print("변경할 직원ID를 입력하세요: ");
//        empId = sc.nextLine();
//        System.out.print("출근정보를 변경하시겠습니까?(Y/N) ");
//        String arrivalAtWork = sc.nextLine();
//        System.out.print("퇴근정보를 변경하시겠습니까?(Y/N) ");
//        String leaveWork = sc.nextLine();
//        System.out.print("지각정보를 변경하시겠습니까?(Y/N) ");
//        String lateWork= sc.nextLine();
//        System.out.print("휴가정보를 변경하시겠습니까?(Y/N) ");
//        String vacation = sc.nextLine();
//        System.out.print("결근정보를 변경하시겠습니까?(Y/N) ");
//        String dayOff = sc.nextLine();
//
//        if (result > 0) {
//            System.out.println("변경 성공!");
//        } else {
//            System.out.println("변경 실패!");
//        }

    }

}














