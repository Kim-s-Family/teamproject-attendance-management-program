package com.ohgiraffers.test;

import com.ohgiraffers.model.dao.EmployeeDAO;
import com.ohgiraffers.model.dto.EmployeePositionDTO;

import java.sql.Connection;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class TestApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection con = getConnection()) {
            EmployeeDAO employeeDAO = new EmployeeDAO();

            System.out.println("로그인을 위해 아이디를 입력하세요: ");
            String empId = scanner.nextLine();
            System.out.println("비밀번호를 입력하세요: ");
            String empPwd = scanner.nextLine();
            EmployeePositionDTO emp_position = employeeDAO.loginEmployee2(con, empId, empPwd);
            if (emp_position != null) {
                System.out.println("로그인 성공: 사용자 권한 확인 중...");
                System.out.println("emp_position" + emp_position.getPositionCode() + " " + emp_position.getPositionName());
                // 사용자 권한에 따른 기능 실행
                employeeDAO.processUserFunctionality(con, emp_position.getAuthorizationAccess(), scanner);
            } else {
                System.out.println("로그인 실패: 아이디 또는 비밀번호를 확인해주세요.");
            }
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다. 프로그램을 종료합니다.");
            e.printStackTrace();
        }
    }
}