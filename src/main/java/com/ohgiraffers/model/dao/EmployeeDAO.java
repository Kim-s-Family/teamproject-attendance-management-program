package com.ohgiraffers.model.dao;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

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

    public List<Map<String, String>> selectAllEmpList(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;

        List<Map<String, String>> empList = null;

        String query = prop.getProperty("selectAllEmpId");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            empList = new ArrayList<>();

            while (rset.next()) {
                Map<String, String> employee = new LinkedHashMap<>();
                employee.put("emp_id", rset.getString("emp_id"));
                employee.put("emp_password", rset.getString("emp_password"));
                employee.put("emp_name", rset.getString("emp_name"));
                employee.put("phone", rset.getString("phone"));
                employee.put("email", rset.getString("email"));
                employee.put("department_code", rset.getString("department_code"));
                employee.put("position_code", rset.getString("position_code"));
                employee.put("emp_identification", rset.getString("emp_identification"));
                employee.put("payment_code", rset.getString("payment_code"));
                empList.add(employee);
            }
            for (Map<String, String> employee : empList) {
                System.out.println(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }

        return empList;
    }

}