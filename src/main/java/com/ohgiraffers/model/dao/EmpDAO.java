package com.ohgiraffers.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EmpDAO {

    private Properties prop = new Properties();

    /* xml 파일을 불러오는 기본생성자 생성 */
    public EmpDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
