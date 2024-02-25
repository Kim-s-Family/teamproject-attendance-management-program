//package com.ohgiraffers.model.dao;
//
//import com.ohgiraffers.model.dto.EmployeePositionDTO;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import static com.ohgiraffers.common.JDBCTemplate.close;
//
//public class newDAO {
//
//    private Properties prop = new Properties();
//
//    /* xml 파일을 불러오는 생성자 */
//    public newDAO() {
//        try {
//            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String loginEmployee(Connection con, String empId, String empPwd) {
//        PreparedStatement pstmt = null;
//        ResultSet rset = null;
//        String emp_position = null;
//        String query = prop.getProperty("loginEmployee");
//
//        try {
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, empId);
//            pstmt.setString(2, empPwd);
//            rset = pstmt.executeQuery();
//            if (rset.next()) {
//                emp_position = rset.getString("positionCode");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            close(pstmt);
//            close(rset);
//        }
//        return emp_position;
//    }
//
//    public void processUserFuctionality(Connection con, String positionCode) {
//        switch (positionCode) {
//            case "ADMIN":
//                adminFunction(con);
//                /* 관리자 기능 */
//                break;
//            case "MGR":
//                managerFunction(con);
//                /* 중간 관리자 기능 */
//                break;
//            case  "EMP":
//                userFunction(con);
//                break;
//        }
//    }
//    public void adminFunction(Connection con) {
//        // 관리자 권한 기능 실행
//        EmployeeDAO.showAdminMenu(con);
//    }
//
//    public void managerFunction(Connection con) {
//        // 중간 관리자 권한 기능 실행
//    }
//
//    public void userFunction(Connection con) {
//        // 일반 사용자 권한 기능 실행
//    }
//
//    public static void showAdminMenu(Connection con) {
//        boolean exit = false;
//        while (!exit) {
//            System.out.println("=== 근태관리 프로그램(ADMIN) ===");
//            System.out.println("메뉴를 선택하세요 : ");
//            System.out.println("1. 직원 정보 입력");
//            System.out.println("2. 직원 정보 수정");
//            System.out.println("3. 직원 정보 삭제");
//            System.out.println("4. 근태조회");
//            System.out.println("5. 종료");
//            int choice = sc.nextInt();
//            sc.nextLine();
//
//            switch (choice) {
//                case 1:
//                    System.out.println("직원 정보 입력 메소드 호출");
//                    break;
//                case 2:
//                    System.out.println("직원 정보 수정 메소드 호출");
//                    break;
//                case 3:
//                    System.out.println("직원정보 삭제 메소드 호출");
//                    break;
//                case 4:
//                    System.out.println("근태조회 메소드 호출");
//                    break;
//                case 5:
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("잘못 입력하셨습니다.");
//            }
//        }
//    }
//    public EmployeePositionDTO loginEmployee2(Connection con, String empId, String empPwd) {
//        PreparedStatement pstmt = null;
//        ResultSet rset = null;
//        EmployeePositionDTO employeePositionDTO = null;
//        String query = prop.getProperty("loginAdminTrueOrFalse");
//
//        try {
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, empId);
//            pstmt.setString(2, empPwd);
//            rset = pstmt.executeQuery();
//            if (rset.next()) {
//                String positionName = rset.getString("position_name");
//                String positionCode = rset.getString("position_code");
//                String authorizationAccess = rset.getString("authorization_access");
//                employeePositionDTO = new EmployeePositionDTO(positionName, positionCode, authorizationAccess);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Database login error", e);
//        } finally {
//            // Closing resources...
//        }
//
//        return employeePositionDTO;
//    }
//}
//}
