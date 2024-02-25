package com.ohgiraffers.test;

import com.ohgiraffers.control.MainMenu;
import com.ohgiraffers.model.dao.EmployeeDAO;

import static com.ohgiraffers.view.AsciiArtPrinter.startLogo;

public class TestApplication {

    public static void main(String[] args) {


        startLogo();

        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MainMenu loginEmp = new MainMenu();
        loginEmp.login();


    }

}
