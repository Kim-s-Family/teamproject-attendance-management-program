package com.ohgiraffers.model.dao;

import com.ohgiraffers.model.dto.EmployeeCompensationDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;
import com.ohgiraffers.model.dto.EmployeePositionDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class EmployeeDAO {

    private Properties prop = new Properties();


    /* xml 파일을 불러오는 생성자 */

    public EmployeeDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* 기능에 따른 로그인 */
    public void processUserFunctionality(Connection con, String positionCode, Scanner sc) {
        switch (positionCode) {
            case "ADMIN":
                adminFunction(sc, this, con);  // adminFunction 메서드를 호출하는 문구 // sc는 Scanner 객체로 사용자로부터 입력을 받기 위해 전달됨, this 는 현재 EmployeeDAO 객체를 가리킴, con은 데이터베이스 연결을 나타내는 Connection 객체임
                /* 관리자 기능 */
                break;
            case "MGR":
                managerFunction(sc, this, con);
                /* 중간 관리자 기능 */
                break;
            case "EMP":
                userFunction(sc, this, con);
                /* 일반 사용자 기능 */
                break;
        }

    }


    public void adminFunction(Scanner sc, EmployeeDAO employeeDAO, Connection con) {
//        관리자 권한 기능 실행
        boolean exit = false;

        while (!exit) {
            System.out.println("=== 근태관리 프로그램(ADMIN) ===");
            System.out.println("1. 직원 정보 입력");
            System.out.println("2. 직원 정보 수정");
            System.out.println("3. 직원 정보 삭제");
            System.out.println("4. 근태조회");
            System.out.println("5. 포상 여부 조회");
            System.out.println("99. 로그아웃");
            System.out.print("메뉴를 선택하세요 : ");
            try {
            int choice = Integer.parseInt(sc.nextLine());  // 사용자로부터 입력을 받기 위해 sc(Scanner) 객체를 사용하고, 그 입력을 정수로 변환하여'choice' 변수에 저장하는 코드. Integer.parseInt() 는 문자열을 정수로 변환해주는 메서드.
            switch (choice) {
                case 1:
                    System.out.println("직원 정보 입력 메소드 호출");
                    break;
                case 2:
                    System.out.println("직원 정보 수정 메소드 호출");
                    break;
                case 3:
                    System.out.println("직원 정보 삭제 메소드 호출");
                    break;
                case 4:
                    System.out.println("근태조회 메소드 호출");
                    break;
                case 5:
                    System.out.println("포상 여부 조회 호출");
                    checkCompensationStatusFuntionality();
                    break;
                case 99:
                    exit = true;
                    System.out.println("로그아웃 되었습니다.");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            } return;
        } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
            }
    }
}
    public void managerFunction(Scanner sc, EmployeeDAO employeeDAO, Connection con) {
//        중간 관리자 권한 기능 실행
        boolean exit = false;

        while (!exit) {
            System.out.println("=== 근태관리 프로그램(MGR) ===");
            System.out.println("1. 부서 내 직원 정보 조회 ");
            System.out.println("2. 부서 내 직원 근태 조회");
            System.out.println("99. 로그아웃");
            System.out.print("메뉴를 선택하세요 : ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("부서 내 직원 정보 조회 메소드 호출");
                    break;
                case 2:
                    System.out.println("부서 내 직원 근태 조회 메소드 호출");
                    break;
                case 99:
                    exit = true;
                    System.out.println("로그아웃 되었습니다.");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    public void userFunction(Scanner sc, EmployeeDAO employeeDAO, Connection con) {
//        일반 사용자 권한 기능 실행
        boolean exit = false;

        while (!exit) {
            System.out.println("=== 근태관리 프로그램(EMP) ===");
            System.out.println("1. 본인 근태 조회 ");
            System.out.println("99. 로그아웃");
            System.out.print("메뉴를 선택하세요 : ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("본인 근태 조회 메소드 호출");
                    break;
                case 99:
                    exit = true;
                    System.out.println("로그아웃 되었습니다.");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }

    }

    /* 로그인 정보 확인 메서드 */
    public EmployeePositionDTO EmployeePositionDTO(Connection con, String empId, String empPwd) {
        PreparedStatement pstmt = null;                   // 객체 초기화
        ResultSet rset = null;                            // 객체 초기화
        EmployeePositionDTO employeePositionDTO = null;   // 객체 초기화
        String query = prop.getProperty("checkLogin");    // 데이터베이스 쿼리를 XML파일에서 읽어옴. 이 쿼리는 사용자의 아이디와 비밀번호를 검증하는 쿼리

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            pstmt.setString(2, empPwd);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                String positionName = rset.getString("position_name");
                String positionCode = rset.getString("position_code");
                String authorizationAccess = rset.getString("authorization_access");
                String empName = rset.getString("emp_name");
                employeePositionDTO = new EmployeePositionDTO(positionName,
                        positionCode,
                        authorizationAccess,
                        empName);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database login error", e);
        }  // try 블록 안에서는 데이터베이스 연결을 이용해서 사용자의 정보를 쿼리함. 그 후 rset(ResultSet) 에 결과를 저장하고 만약 결과가 존재한다면 사용자의 정보를 가져와 EmployeePositionDTO 객체를 생성함

        return employeePositionDTO;
    }

    public static void loginAndProcessFuntionality() {
        Scanner sc = new Scanner(System.in);

        try (Connection con = getConnection()) {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            boolean exitProgram = false;

            while (!exitProgram) {
                System.out.println("로그인을 위해 'login'을 입력하세요. 프로그램을 종료하려면 'exit'을 입력하세요. ");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("login")) {
                    System.out.print("로그인을 위해 아이디를 입력하세요: ");
                    String empId = sc.nextLine();
                    System.out.print("비밀번호를 입력하세요: ");
                    String empPwd = sc.nextLine();
                    EmployeePositionDTO emp_position = employeeDAO.EmployeePositionDTO(con, empId, empPwd);

                    if (emp_position != null) {
                        System.out.println("로그인 성공: 사용자 권한 확인 중...");
                        System.out.println("emp_position" + emp_position.getPositionCode() + " " + emp_position.getPositionName() + " " + emp_position.getEmpName());
                        // 사용자 권한에 따른 기능 실행
                        employeeDAO.processUserFunctionality(con, emp_position.getAuthorizationAccess(), sc);
                    } else {
                        System.out.println("로그인 실패: 아이디 또는 비밀번호를 확인해주세요.");
                    }

                } else if (input.equalsIgnoreCase("exit")) {
                    exitProgram = true;
                } else {
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                }
            }
        } catch (SQLException e) {
            System.out.println("오류가 발생했습니다. 프로그램을 종료합니다.");
            e.printStackTrace();
        } finally {
            sc.close();
            System.out.println("프로그램을 종료합니다.");
        }
    }

    public EmployeeCompensationDTO EmployeeCompensationDTO(Connection con, String empId, String monthCode) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        EmployeeCompensationDTO employeeCompensationDTO = null;
        String query = prop.getProperty("checkCompensationStatus");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            pstmt.setString(2, monthCode);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                String monthCodeResult = rset.getString("month_code");
                String compensationStatus = rset.getString("compensation_status");
                String penaltyStatus = rset.getString("penalty_status");
                String penaltyScoreSum = rset.getString("penalty_score_sum");
                employeeCompensationDTO = new EmployeeCompensationDTO(empId,
                        monthCodeResult,
                        compensationStatus,
                        penaltyStatus,
                        penaltyScoreSum);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rset != null) rset.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return employeeCompensationDTO;
    }

    public static void checkCompensationStatusFuntionality() {
        Scanner sc = new Scanner(System.in);
        boolean returnProgram = false;

        while (!returnProgram) {
            try (Connection con = getConnection()) {
                EmployeeDAO employeeDAO = new EmployeeDAO();

                while (true) {
                    System.out.print("직원 ID를 입력해주세요: ");
                    String empId = sc.nextLine();
                    System.out.print("월별 근태 조회 해당 '월' 선택(예시: 1월 '월 붙여서 검색해야함'): ");
                    String monthCode = sc.nextLine();

                    EmployeeCompensationDTO employeeCompensationDTO = employeeDAO.EmployeeCompensationDTO(con, empId, monthCode);

                    if (employeeCompensationDTO != null) {
                        System.out.println("포상 여부 조회 결과:");
                        System.out.println("직원 ID: " + employeeCompensationDTO.getEmpId());
                        System.out.println("월 코드: " + employeeCompensationDTO.getMonthCode());
                        System.out.println("보상 여부: " + employeeCompensationDTO.getCompensationStatus());
                        System.out.println("페널티 여부: " + employeeCompensationDTO.getPenaltyStatus());
                        System.out.println("페널티 점수 합계: " + employeeCompensationDTO.getPenaltyScoreSum());
                    } else {
                        System.out.println("해당 직원 ID의 정보가 없거나 " + monthCode + "월 데이터가 없습니다.");
                        continue;
                    }

                    while (true) {
                        System.out.println("menu로 돌아가시려면 'menu'를 입력하세요. 계속 조회를 원하시면 'continue'를 입력하세요.");
                        String input = sc.nextLine();
                        if (input.equalsIgnoreCase("menu")) {
                            returnProgram = true;
                            break;
                        } else if (input.equalsIgnoreCase("continue")) {
                            break;
                        } else {
                            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");

                        }
                    }
                    break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}


