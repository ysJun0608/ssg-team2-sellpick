package delivery.dao;

import DBIO.ObjectDBIO;
import delivery.domain.DeliveryCmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryCmpDao extends ObjectDBIO {
    Scanner sc = new Scanner(System.in);
    Connection conn = null;

    //택배사 리스트를 보여주고 택배사를 결정하게 하는 메소드
    public DeliveryCmp chooseCompany() {
        ArrayList<DeliveryCmp> deliveryCmpList = new ArrayList<>();
        conn = open();
        try {
            System.out.println("현재 셀픽과 계약된 택배사 입니다.");
            String companysql = "SELECT * FROM company";

            PreparedStatement pstmt = conn.prepareStatement(companysql);
            ResultSet resultSet = pstmt.executeQuery(companysql);


            while (resultSet.next()) {
                DeliveryCmp deliveryCmp = new DeliveryCmp();
                deliveryCmp.setId(resultSet.getLong("id"));
                deliveryCmp.setName(resultSet.getString("name"));
                deliveryCmpList.add(deliveryCmp);
            }
            resultSet.close();

            pstmt.close();
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (DeliveryCmp deliveryCmp : deliveryCmpList)
            System.out.println(deliveryCmp.toString());

        System.out.println("---------");
        System.out.println("이용하실 택배사 번호를 입력해주세요");

        int deliveryCompany = Integer.parseInt(sc.nextLine());
        for (DeliveryCmp deliveryCmp : deliveryCmpList) {
            if (deliveryCmp.getId() == deliveryCompany) {
                return deliveryCmp;
            }
        }
        System.out.println("없는 택배사 입니다.");
        return null;
    }
}