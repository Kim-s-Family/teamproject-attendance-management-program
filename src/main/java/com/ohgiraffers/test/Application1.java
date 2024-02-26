package com.ohgiraffers.test;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;


public class Application1 {

    private Properties prop = new Properties();

    public Application1() {

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkId(String empId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        boolean exists = false;

        try {

            con = getConnection();
            String query = "SELECT COUNT(*) FROM emp_info WHERE emp_id = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                int count = rset.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        return exists;
    }

    public void changeEmpInfo() {
        Scanner sc = new Scanner(System.in);
        EmployeeDTO changeEmp = new EmployeeDTO();
        String empId = "";

        while (true) {
            System.out.print("정보를 변경하실 직원의 ID를 입력하세요 : ");
            empId = sc.nextLine();
            if (checkId(empId)) {
                break;
            } else {
                System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
            }
        }
        changeEmp.setEmpId(empId);

        System.out.println("원하시는 항목을 선택해주세요:");
        System.out.println("1. Password 변경");
        System.out.println("2. 이름 변경");
        System.out.println("3. 연락처 변경");
        System.out.println("4. email 변경");
        System.out.println("5. 부서코드 변경");
        System.out.println("6. 직책코드 변경");
        System.out.println("7. 권한 코드 변경");
        System.out.println("8. 급여 코드 변경");

        System.out.print("선택 : ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("변경하실 Password를 입력하세요 : ");
                String empPwd = sc.nextLine();
                if (!empPwd.isEmpty()) {
                    changeEmp.setEmpPwd(empPwd);
                }
                break;
            case 2:
                System.out.print("변경하실 이름을 입력하세요 : ");
                String empName = sc.nextLine();
                if (!empName.isEmpty()) {
                    changeEmp.setEmpName(empName);
                }
                break;
            case 3:
                System.out.print("변경하실 연락처를 입력하세요(하이픈 '-'포함) : ");
                String phone = sc.nextLine();
                if (!phone.isEmpty()) {
                    changeEmp.setPhone(phone);
                }
                break;
            case 4:
                System.out.print("변경하실 email을 입력하세요 : ");
                String email = sc.nextLine();
                if (!email.isEmpty()) {
                    changeEmp.setEmail(email);
                }
                break;
            case 5:
                System.out.println("[DP1 : 경영지원팀] / [DP2 : 영업팀] / [DP3 : 개발팀] / [DP4 : 마케팅팀] / [DP5 : 고객서비스팀]");
                System.out.print("변경하실 부서코드를 입력하세요 : ");
                String departmentCode = sc.nextLine().toUpperCase();
                if (!departmentCode.isEmpty()) {
                    changeEmp.setDepartmentCode(departmentCode);
                }
                break;
            case 6:
                System.out.println("[PS1 : 사장] / [PS2 : 부장] / [PS3 : 과장] / [PS4 : 대리] / [PS5 : 사원]");
                System.out.print("변경하실 직책코드를 입력하세요 : ");
                String positionCode = sc.nextLine().toUpperCase();
                if (!positionCode.isEmpty()) {
                    changeEmp.setPositionCode(positionCode);
                }
                break;
            case 7:
                System.out.println("[ADMIN : 관리자] / [MGR : 중간관리자] / [EMP : 일반직원]");
                System.out.print("변경하실 권한 코드를 입력하세요 : ");
                String empIdentification = sc.nextLine().toUpperCase();
                if (!empIdentification.isEmpty()) {
                    changeEmp.setEmpIdentification(empIdentification);
                }
                break;
            case 8:
                System.out.println("[PM1 : 6,000,000] / [PM2 : 5,000,000] / [PM3 : 4,000,000] / [PM4 : 3,200,000] / [PM5 : 2,500,000]");
                System.out.print("변경하실 급여 코드를 입력하세요 : ");
                String paymentCode = sc.nextLine().toUpperCase();
                if (!paymentCode.isEmpty()) {
                    changeEmp.setPaymentCode(paymentCode);
                }
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                break;
        }

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String query = prop.getProperty("changeEmpInfo");

            boolean isFirst = true;

            if (changeEmp.getEmpPwd() != null) {
                query += (isFirst ? "" : ", ") + "emp_password = ?";
                isFirst = false;
            }
            if (changeEmp.getEmpName() != null) {
                query += (isFirst ? "" : ", ") + "emp_name = ?";
                isFirst = false;
            }
            if (changeEmp.getPhone() != null) {
                query += (isFirst ? "" : ", ") + "phone = ?";
                isFirst = false;
            }
            if (changeEmp.getEmail() != null) {
                query += (isFirst ? "" : ", ") + "email = ?";
                isFirst = false;
            }
            if (changeEmp.getDepartmentCode() != null) {
                query += (isFirst ? "" : ", ") + "department_code = ?";
                isFirst = false;
            }
            if (changeEmp.getPositionCode() != null) {
                query += (isFirst ? "" : ", ") + "position_code = ?";
                isFirst = false;
            }
            if (changeEmp.getEmpIdentification() != null) {
                query += (isFirst ? "" : ", ") + "emp_identification = ?";
                isFirst = false;
            }
            if (changeEmp.getPaymentCode() != null) {
                query += (isFirst ? "" : ", ") + "payment_code = ?";
                isFirst = false;
            }

            query += " WHERE emp_id = ?";

            pstmt = con.prepareStatement(query);


            int index = 1;
            if (changeEmp.getEmpPwd() != null) {
                pstmt.setString(index++, changeEmp.getEmpPwd());
            }
            if (changeEmp.getEmpName() != null) {
                pstmt.setString(index++, changeEmp.getEmpName());
            }
            if (changeEmp.getPhone() != null) {
                pstmt.setString(index++, changeEmp.getPhone());
            }
            if (changeEmp.getEmail() != null) {
                pstmt.setString(index++, changeEmp.getEmail());
            }
            if (changeEmp.getDepartmentCode() != null) {
                pstmt.setString(index++, changeEmp.getDepartmentCode());
            }
            if (changeEmp.getPositionCode() != null) {
                pstmt.setString(index++, changeEmp.getPositionCode());
            }
            if (changeEmp.getPaymentCode() != null) {
                pstmt.setString(index++, changeEmp.getPaymentCode());
            }

            pstmt.setString(index, changeEmp.getEmpId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }
        if (result > 0) {
            System.out.println("직원 변경 성공");
        } else {
            System.out.println("직원 변경 실패");
        }
    }
}
