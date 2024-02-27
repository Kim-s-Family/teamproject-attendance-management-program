package com.ohgiraffers.model.dao;


import com.mysql.cj.util.StringInspector;
import com.ohgiraffers.model.dto.EmployeeDTO;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public int insertNewEmployee() {

        Scanner sc = new Scanner(System.in);
        EmployeeDTO changeInfo = new EmployeeDTO();

        System.out.print("등록하실 직원의 ID를 입력하세요 : ");
        String empId = sc.nextLine();
        changeInfo.setEmpId(empId);


        System.out.print("사용하실 Password를 입력하세요 : ");
        String empPwd = sc.nextLine();
        changeInfo.setEmpPwd(empPwd);


        System.out.print("등록하실 이름을 입력하세요 : ");
        String empName = sc.nextLine();
        changeInfo.setEmpName(empName);


        System.out.print("등록하실 연락처를 입력하세요(하이픈 '-'포함) : ");
        String phone = sc.nextLine();
        changeInfo.setPhone(phone);


        System.out.print("등록하실 email을 입력하세요 : ");
        String email = sc.nextLine();
        changeInfo.setEmail(email);


        while (true) {
            System.out.println("[DP1 : 경영지원팀] / [DP2 : 영업팀] / [DP3 : 개발팀] / [DP4 : 마케팅팀] / [DP5 : 고객서비스팀]");
            System.out.print("부서코드를 입력하세요 : ");
            String departmentCode = sc.nextLine().toUpperCase();
            if (!departmentCode.isEmpty()) {

                switch (departmentCode) {
                    case "DP1":
                        changeInfo.setDepartmentCode(departmentCode);
                        break;
                    case "DP2":
                        changeInfo.setDepartmentCode(departmentCode);
                        break;
                    case "DP3":
                        changeInfo.setDepartmentCode(departmentCode);
                        break;
                    case "DP4":
                        changeInfo.setDepartmentCode(departmentCode);
                        break;
                    case "DP5":
                        changeInfo.setDepartmentCode(departmentCode);
                        break;
                }
                break;
            } else {
                System.out.println("잘못된 부서코드입니다. 다시 입력해주세요.");
            }
        }

        while (true) {
            System.out.println("[PS1 : 사장] / [PS2 : 부장] / [PS3 : 과장] / [PS4 : 대리] / [PS5 : 사원]");
            System.out.print("직책코드를 입력하세요 : ");
            String positionCode = sc.nextLine().toUpperCase();

            if (!positionCode.isEmpty()) {

                switch (positionCode) {
                    case "PS1":
                        changeInfo.setPositionCode(positionCode);
                        break;
                    case "PS2":
                        changeInfo.setPositionCode(positionCode);
                        break;
                    case "PS3":
                        changeInfo.setPositionCode(positionCode);
                        break;
                    case "PS4":
                        changeInfo.setPositionCode(positionCode);
                        break;
                    case "PS5":
                        changeInfo.setPositionCode(positionCode);
                        break;
                }
                break;
            } else {
                System.out.println("잘못된 직책코드입니다. 다시 입력해주세요.");
            }
        }

        while (true) {
            System.out.println("[ADMIN : 관리자] / [MGR : 중간관리자] / [EMP : 일반직원]");
            System.out.print("권한 코드를 입력하세요 : ");
            String empIdentification = sc.nextLine().toUpperCase();

            if (!empIdentification.isEmpty()) {

                switch (empIdentification) {
                    case "ADMIN":
                        changeInfo.setEmpIdentification(empIdentification);
                        break;
                    case "MGR":
                        changeInfo.setEmpIdentification(empIdentification);
                        break;
                    case "EMP":
                        changeInfo.setEmpIdentification(empIdentification);
                        break;
                }
                break;
            } else {
                System.out.println("잘못된 권한코드입니다. 다시 입력해주세요.");
            }
        }

        while (true) {
            System.out.println("[PM1 : 6,000,000] / [PM2 : 5,000,000] / [PM3 : 4,000,000] / [PM4 : 3,200,000] / [PM5 : 2,500,000]");
            System.out.print("급여 코드를 입력하세요 : ");
            String paymentCode = sc.nextLine().toUpperCase();

            if (!paymentCode.isEmpty()) {

                switch (paymentCode) {
                    case "PM1":
                        changeInfo.setPaymentCode(paymentCode);
                        break;
                    case "PM2":
                        changeInfo.setPaymentCode(paymentCode);
                        break;
                    case "PM3":
                        changeInfo.setPaymentCode(paymentCode);
                        break;
                    case "PM4":
                        changeInfo.setPaymentCode(paymentCode);
                        break;
                    case "PM5":
                        changeInfo.setPaymentCode(paymentCode);
                        break;
                }
                break;
            } else {
                System.out.println("잘못된 급여코드입니다. 다시 입력해주세요.");
            }
        }

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;


        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("insertNewEmployee");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, changeInfo.getEmpId());
            pstmt.setString(2, changeInfo.getEmpPwd());
            pstmt.setString(3, changeInfo.getEmpName());
            pstmt.setString(4, changeInfo.getPhone());
            pstmt.setString(5, changeInfo.getEmail());
            pstmt.setString(6, changeInfo.getDepartmentCode());
            pstmt.setString(7, changeInfo.getPositionCode());
            pstmt.setString(8, changeInfo.getEmpIdentification());
            pstmt.setString(9, changeInfo.getPaymentCode());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }
        if (result > 0) {
            System.out.println("직원 등록 성공");
        } else {
            System.out.println("직원 등록 실패");
        }

        return insertNewEmployee();

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


//    public void changeEmpInfo() {
//        Scanner sc = new Scanner(System.in);
//        EmployeeDTO changeEmp = new EmployeeDTO();
//        String empId;
//
//        while (true) {
//
//            System.out.print("변경하실 직원의 ID를 입력하세요 : ");
//            empId = sc.nextLine();
//
//            if (checkId(empId)) {
//                break;
//            } else {
//                System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
//            }
//        }
//        changeEmp.setEmpId(empId);
//
//
//        System.out.println("원하시는 항목을 선택해주세요:");
//        System.out.println("1. Password 변경");
//        System.out.println("2. 이름 변경");
//        System.out.println("3. 연락처 변경");
//        System.out.println("4. email 변경");
//        System.out.println("5. 부서코드 변경");
//        System.out.println("6. 직책코드 변경");
//        System.out.println("7. 권한 코드 변경");
//        System.out.println("8. 급여 코드 변경");
//        int num = sc.nextInt();
//
//        switch (num) {
//            case 1:
//                System.out.println("1. Password 변경");
//                String empPwd = sc.nextLine();
//                if (!empPwd.isEmpty()) {
//                    changeEmp.setEmpPwd(empPwd);
//                }
//                break;
//
//            case 2:
//                System.out.println("2. 이름 변경");
//                String empName = sc.nextLine();
//                if (!empName.isEmpty()) {
//                    changeEmp.setEmpName(empName);
//                }
//                break;
//
//            case 3:
//                System.out.println("3. 연락처 변경");
//                String phone = sc.nextLine();
//                if (!phone.isEmpty()) {
//                    changeEmp.setPhone(phone);
//                }
//
//                break;
//
//            case 4:
//                System.out.println("4. email 변경");
//                String email = sc.nextLine();
//                if (!email.isEmpty()) {
//                    changeEmp.setEmail(email);
//                }
//
//                break;
//
//            case 5:
//                System.out.println("5. 부서코드 변경");
//                String departmentCode = sc.nextLine();
//                if (!departmentCode.isEmpty()) {
//                    changeEmp.setPositionCode(departmentCode);
//                }
//                break;
//
//            case 6:
//                System.out.println("6. 직책코드 변경");
//                String positionCode = sc.nextLine();
//                if (!positionCode.isEmpty()) {
//                    changeEmp.setPositionCode(positionCode);
//                }
//                break;
//
//
//            case 7:
//                System.out.println("7. 권한 코드 변경");
//                String empIdentification = sc.nextLine();
//                if (!empIdentification.isEmpty()) {
//                    changeEmp.setEmpIdentification(empIdentification);
//                }
//                break;
//
//            case 8:
//                System.out.println("8. 급여 코드 변경");
//                String paymentCode = sc.nextLine();
//                if (!paymentCode.isEmpty()) {
//                    changeEmp.setPaymentCode(paymentCode);
//                }
//                break;
//            default:
//                System.out.println("잘못된 선택입니다.");
//                break;
//        }
//
//
//        Connection con = getConnection();
//        PreparedStatement pstmt = null;
//        int result = 0;
//
//
//
//        Properties prop = new Properties();
//        try {
//            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
//            String query = prop.getProperty("changeEmpInfo");
//
//
//            if (changeEmp.getEmpPwd()!=null) {
//
//            }
//
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, changeEmp.getEmpId());
//            pstmt.setString(2, changeEmp.getEmpPwd());
//            pstmt.setString(3, changeEmp.getEmpName());
//            pstmt.setString(4, changeEmp.getPhone());
//            pstmt.setString(5, changeEmp.getEmail());
//            pstmt.setString(6, changeEmp.getDepartmentCode());
//            pstmt.setString(7, changeEmp.getPositionCode());
//            pstmt.setString(8, changeEmp.getEmpIdentification());
//            pstmt.setString(9, changeEmp.getPaymentCode());
//
//
//            result = pstmt.executeUpdate();
//
//
//        } catch (
//                IOException | SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            close(con);
//            close(pstmt);
//        }
//        if (result > 0) {
//            System.out.println("직원 변경 성공");
//        } else {
//            System.out.println("직원 변경 실패");
//        }
//    }
//}
//
//
//

    public void changeEmpInfo() {

        Scanner sc = new Scanner(System.in);
        EmployeeDTO changeEmpInfo = new EmployeeDTO();
        String empId;
        String empPwd = null;
        String empName;
        String phone;
        String email;
        String departmentCode;
        String positionCode;
        String empIdentification;
        String paymentCode;

        while (true) {

            System.out.print("변경하실 직원의 ID를 입력하세요 : ");
            empId = sc.nextLine();

            if (checkId(empId)) {
                break;
            } else {
                System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
            }
        }
        changeEmpInfo.setEmpId(empId);

        System.out.println("원하시는 항목을 선택해주세요:");
        System.out.println("1. Password 변경");
        System.out.println("2. 이름 변경");
        System.out.println("3. 연락처 변경");
        System.out.println("4. email 변경");
        System.out.println("5. 부서코드 변경");
        System.out.println("6. 직책코드 변경");
        System.out.println("7. 권한 코드 변경");
        System.out.println("8. 급여 코드 변경");
        int num = sc.nextInt();


        if (num == 1) {
            System.out.print("변경하실 Password를 입력하세요 : ");
            sc.nextLine();
            if (!empPwd.isEmpty()) {
                changeEmpInfo.setEmpPwd(empPwd);
            }
        }




        if (num == 2) {
            System.out.print("변경하실 이름을 입력하세요 : ");
            empName = sc.nextLine();
        }


        if (num == 3) {
            System.out.print("변경하실 연락처를 입력하세요(하이픈 '-'포함) : ");
            phone = sc.nextLine();
        }



        if (num == 4) {
            System.out.print("변경하실 email을 입력하세요 : ");
            email = sc.nextLine();
        }



        if (num == 5) {
            System.out.println("[DP1 : 경영지원팀] / [DP2 : 영업팀] / [DP3 : 개발팀] / [DP4 : 마케팅팀] / [DP5 : 고객서비스팀]");
            System.out.print("변경하실 부서코드를 입력하세요 : ");
            departmentCode = sc.nextLine().toUpperCase();

        }


        if (num == 6) {

            System.out.println("[PS1 : 사장] / [PS2 : 부장] / [PS3 : 과장] / [PS4 : 대리] / [PS5 : 사원]");
            System.out.print("변경하실 직책코드를 입력하세요 : ");
            positionCode = sc.nextLine().toUpperCase();

        }



        if (num == 7) {
            System.out.println("[ADMIN : 관리자] / [MGR : 중간관리자] / [EMP : 일반직원]");
            System.out.print("변경하실 권한 코드를 입력하세요 : ");
            empIdentification = sc.nextLine().toUpperCase();
        }


        if (num == 8) {
            System.out.println("[PM1 : 6,000,000] / [PM2 : 5,000,000] / [PM3 : 4,000,000] / [PM4 : 3,200,000] / [PM5 : 2,500,000]");
            System.out.print("변경하실 급여 코드를 입력하세요 : ");
            paymentCode = sc.nextLine().toUpperCase();
        }

        changeEmpInfo = new EmployeeDTO();

//        changedInfo.setEmpName(empName);
//        changedInfo.setPhone(phone);
//        changedInfo.setEmail(email);
//        changedInfo.setDepartmentCode(departmentCode);
//        changedInfo.setPositionCode(positionCode);
//        changedInfo.setEmpIdentification(empIdentification);
//        changedInfo.setPaymentCode(paymentCode);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;


        Properties prop = new Properties();
        try {

            String query = prop.getProperty("changeEmpInfo");

            query += num;


            pstmt = con.prepareStatement(query);

            pstmt.setString(1, changeEmpInfo.getEmpId());
            pstmt.setString(2, changeEmpInfo.getEmpPwd());
            pstmt.setString(3, changeEmpInfo.getEmpName());
            pstmt.setString(4, changeEmpInfo.getPhone());
            pstmt.setString(5, changeEmpInfo.getEmail());
            pstmt.setString(6, changeEmpInfo.getDepartmentCode());
            pstmt.setString(7, changeEmpInfo.getPositionCode());
            pstmt.setString(8, changeEmpInfo.getEmpIdentification());
            pstmt.setString(9, changeEmpInfo.getPaymentCode());

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




































