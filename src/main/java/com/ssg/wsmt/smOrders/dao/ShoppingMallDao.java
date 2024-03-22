package com.ssg.wsmt.smOrders.dao;

import com.ssg.wsmt.DBIO.ObjectDBIO;
import com.ssg.wsmt.smOrders.domain.ShoppingMallVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingMallDao extends ObjectDBIO {
    Connection conn = null;

    public List<ShoppingMallVo> findAll() {
        conn = open();
        List<ShoppingMallVo> shoppingMallList = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM SHOPPING_MALL";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ShoppingMallVo sm = new ShoppingMallVo();
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
