package com.ohgiraffers.test;


import com.ohgiraffers.model.dao.EmployeeDAO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import javax.swing.*;
import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class TestApplication{

    public static void main(String[] args) {

        /*정보입력 InsertNewEmployee*/
//        EmployeeDAO registerDAO = new EmployeeDAO();
//        registerDAO.insertNewEmployee();

        /*정보수정 changeEmpInfo*/
        EmployeeDAO changeDAO=new EmployeeDAO();
        changeDAO.changeEmpInfo();


    }
}














