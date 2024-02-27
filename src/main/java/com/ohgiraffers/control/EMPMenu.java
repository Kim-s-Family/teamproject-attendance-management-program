package com.ohgiraffers.control;

import java.util.Scanner;

public class EMPMenu {

    /* 일반직원으로 로그인 했을 시 메뉴 화면 */
    public void empMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓     근태관리프로그램(EMP)     ▓▓");
        System.out.println("▓▓    1. 직원정보조회(본인)      ▓▓");
        System.out.println("▓▓    2. 근태기록조회(본인)      ▓▓");
        System.out.println("▓▓    3. 포상여부조회(본인)      ▓▓");
        System.out.println("▓▓    4. 로 그 아 웃           ▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println();
        System.out.print("메뉴를 선택해 주세요 : ");
        int choiceNumber = sc.nextInt();

        switch (choiceNumber) {

            case 1:
                System.out.println("직원정보조회(본인) 메서드 호출");
                break;
            case 2:
                System.out.println("근태기록조회(본인) 메서드 호출");
                break;
            case 3:
                System.out.println("포상여부조회(본인) 메서드 호출");
                break;
            case 4:
                emplogOut();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                empMenu();
                break;
        }

    }

    /* 일반직원 로그아웃 메서드 */
    public void emplogOut() {

        Scanner sc = new Scanner(System.in);

        System.out.print("로그아웃 하시겠습니까? (Y/N)");
        String typing = sc.nextLine().toUpperCase();

        switch (typing) {

            case "Y":
                System.out.println("로그아웃 되었습니다.");
                MainMenu goToMainMenu = new MainMenu();
                goToMainMenu.login();
                break;
            case "N":
                System.out.println("메뉴 선택창으로 돌아갑니다.");
                empMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                emplogOut();
                break;
        }

    }

}
