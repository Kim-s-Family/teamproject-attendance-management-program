package com.ohgiraffers.test;

import com.ohgiraffers.model.dao.EmployeeDAO;
import com.ohgiraffers.model.dto.EmployeeDTO;

public class TestApplication {

    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();


        employeeDAO.changeEmpInfo();



    }

}
