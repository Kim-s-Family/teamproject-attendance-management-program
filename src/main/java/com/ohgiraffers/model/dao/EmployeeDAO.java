package com.ohgiraffers.model.dao;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;


public class EmployeeDAO {

    private Properties prop = new Properties();

    public EmployeeDAO() {

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int selectAllEmpCount(Connection con) {
        con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;
        int count = 0;
        String query2 = prop.getProperty("countId");

        try {

            stmt = con.createStatement();

            rset = stmt.executeQuery(query2);

            if (rset.next()) {
                count = rset.getInt("total_sum");
            }
            System.out.println("총 직원 수 = "+ count);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            close(rset);
            close(stmt);
        }

        return count;
    }

}