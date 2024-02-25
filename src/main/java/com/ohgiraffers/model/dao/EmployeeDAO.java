package com.ohgiraffers.model.dao;

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


public class EmployeeDAO {

    private Properties prop = new Properties();

    /* xml 파일을 불러오는 기본생성자 생성 */
    public EmployeeDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}


