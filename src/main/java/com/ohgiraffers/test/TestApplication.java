package com.ohgiraffers.test;

import com.ohgiraffers.model.dao.EmployeeDAO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class TestApplication {

    public static void main(String[] args) {
        Connection con = getConnection();

        EmployeeDAO employeeDAO = new EmployeeDAO();


        employeeDAO.selectAllEmpCount(con);



    }

}
