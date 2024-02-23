package com.ohgiraffers.test;

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

        Properties prop = new Properties();

        PreparedStatement pstmt = null;  //메뉴를 추가

        int result = 0;

        Scanner sc = new Scanner(System.in);
        System.out.print("등록할 직원ID를 입력하세요: ");
        String empid = sc.nextLine();
        System.out.print("등록할 직원PWD를 입력하세요: ");
        String emppwd = sc.nextLine();
        System.out.print("카테고리를 선택해주세요(식사,음료,디저트,한식,퓨전): ");
        sc.nextLine();
        String categoryName = sc.nextLine();
        System.out.print("바로 판매 메뉴에 적용하시겠습니까?(에/아니오): ");
        String answer = sc.nextLine();
    }
}














