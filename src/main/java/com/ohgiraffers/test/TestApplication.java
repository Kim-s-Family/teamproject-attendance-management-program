package com.ohgiraffers.test;


import com.ohgiraffers.model.dao.EmployeeDAO;
import com.ohgiraffers.model.dto.CreateEmployeeDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.util.List;
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
                boolean running = true;
                while (running) {
                    System.out.println("\n===== 메뉴를 선택하세요 =====");
                    System.out.println("1. 직원 목록 조회");
                    System.out.println("2. 새 직원 추가");
                    System.out.println("3. 로그아웃");
                    System.out.print("선택: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (choice) {
                        case 1:
                            List<EmployeeDTO> employeeList = employeeDAO.listEmployee(con);
                            for (EmployeeDTO employee : employeeList) {
                                System.out.println(employee);
                            }
                            break;
                        case 2:
                            // Assuming you have a method for creating an employee and capturing user input
                            System.out.println("새 직원 정보를 입력해주세요.");
                            // Capture employee details from user, create a CreateEmployeeDTO object, and call employeeDAO.createEmployee
                            // Example:
                            // CreateEmployeeDTO newEmployee = new CreateEmployeeDTO(...);
                            // employeeDAO.createEmployee(con, newEmployee);
                            System.out.println("직원 추가 기능은 현재 구현중입니다.");
                            break;
                        case 3:
                            System.out.println("로그아웃합니다.");
                            running = false;
                            break;
                        default:
                            System.out.println("잘못된 선택입니다. 다시 시도하세요.");
                    }
                }
            } else {
                System.out.println("로그인 실패: 아이디 또는 비밀번호를 확인해주세요.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    }

}