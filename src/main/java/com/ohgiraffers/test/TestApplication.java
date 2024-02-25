// TestApplication.java
package com.ohgiraffers.test;

import com.ohgiraffers.model.dao.EmployeeDAO;

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

            String loginResult = employeeDAO.loginEmployee(con, empId, empPwd);
            if (loginResult != null) {
                System.out.println(loginResult);
                employeeDAO.processUserFunctionality(con, loginResult);
            } else {
                System.out.println("로그인 실패: 아이디 또는 비밀번호를 확인해주세요.");
            }
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다. 프로그램을 종료합니다.");
            e.printStackTrace();
        }
    }
}
