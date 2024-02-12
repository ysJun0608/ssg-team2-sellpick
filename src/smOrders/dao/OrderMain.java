package smOrders.dao;

import java.util.Scanner;
public class OrderMain {

    static  Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        SMOrdersDao dao = new SMOrdersDao();

        System.out.println("====================================================");
        System.out.println("메인메뉴: 1.Create  |  2.Read  |  3.Update |  4.Clear  | 4.Exit  ");
        System.out.print("메뉴선택:");
        String str = sc.nextLine(); //메뉴 선택 입력 값
        switch (str) { //str ""
            case "1" -> dao.create();
            case "2" -> dao.read();
//            case "3" -> clear();
//            case "4" -> exit();
//            default -> list();

        }
    }


}
