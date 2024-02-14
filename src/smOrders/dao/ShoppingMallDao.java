package smOrders.dao;

import DBIO.ObjectDBIO;
import smOrders.domain.ShoppingMall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingMallDao extends ObjectDBIO {
    Connection conn = null;

    public List<ShoppingMall> findAll() {
        conn = open();
        List<ShoppingMall> shoppingMallList = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM SHOPPING_MALL";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ShoppingMall sm = new ShoppingMall();
                sm.setId(rs.getLong("ID"));
                sm.setName(rs.getString("NAME"));

                shoppingMallList.add(sm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shoppingMallList;
    }
}
