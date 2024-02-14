package mgtOrders.dao;

import DBIO.ObjectDBIO;
import mgtOrders.domain.MgtOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class MgtOrderDao extends ObjectDBIO {
    Connection conn = null;

    public int createOrder(String purChaser, Timestamp createdAt) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = "";
        int id = 0;
        conn = open();

        sql = "INSERT INTO mgt_orders(PURCHASER, STATUS, CREATED_AT) VALUES (?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, purChaser);
        pstmt.setString(2, "READY");
        pstmt.setTimestamp(3, createdAt);
        pstmt.executeUpdate();
        sql = "SELECT MAX(id) AS ID FROM mgt_orders";
        pstmt = conn.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt("ID");
        }

        if (pstmt != null)
            pstmt.close();
        if (conn != null)
            conn.close();

        return id;
    }

    public boolean addItem(int id, Map<Integer, Integer> products) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = "";
        int flag = 0;
        conn = open();

        for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
            sql = "INSERT INTO mgt_orders_products_relationship(QUANTITY, PRODUCTS_ID, MGT_ORDERS_ID) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, entry.getValue());
            pstmt.setInt(2, entry.getKey());
            pstmt.setInt(3, id);
            flag = pstmt.executeUpdate();
            if (flag == 0) {
                break;
            }
        }

        if (pstmt != null)
            pstmt.close();
        if (conn != null)
            conn.close();

        return flag > 0 ? true : false;
    }


    public boolean confirmOrder(int orderId) throws SQLException {
        PreparedStatement pstmt = null;
        int flag = 0;
        conn = open();
        String sql = "UPDATE mgt_orders SET STATUS = ? WHERE id = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "DONE");
        pstmt.setInt(2, orderId);
        flag = pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();
        if (conn != null)
            conn.close();

        return flag > 0 ? true : false;
    }


    public boolean cancelOrder(int orderId) throws SQLException {
        PreparedStatement pstmt = null;
        int flag = 0;
        conn = open();
        String sql = "UPDATE mgt_orders SET STATUS = ? WHERE id = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "READY");
        pstmt.setInt(2, orderId);
        flag = pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();
        if (conn != null)
            conn.close();

        return flag > 0 ? true : false;
    }


    public ArrayList<MgtOrder> selectAll(String startDate, String endDate) throws SQLException {
        PreparedStatement pstmt = null;
        ArrayList<MgtOrder> searchList = new ArrayList<>();
        conn = open();
        String searchQuery = "SELECT * FROM mgt_orders " +
                "WHERE CREATED_AT " +
                "BETWEEN ? and ?+0.999999999";

        pstmt = conn.prepareStatement(searchQuery);
        pstmt.setString(1, startDate);
        pstmt.setString(2, endDate);
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String purchaser = resultSet.getString("PURCHASER");
            String tempStatus = resultSet.getString("STATUS");
            Timestamp date = resultSet.getTimestamp("CREATED_AT");
            MgtOrder mgtOrder = new MgtOrder(id, purchaser, tempStatus, date);
            searchList.add(mgtOrder);
        }

        if (pstmt != null)
            pstmt.close();
        if (conn != null)
            conn.close();

        return searchList;
    }


    public ArrayList<MgtOrder> selectOrderList(String status) throws SQLException {
        PreparedStatement pstmt = null;
        ArrayList<MgtOrder> mgtOrders = new ArrayList<>();
        conn = open();
        String sql = "SELECT * FROM mgt_orders WHERE STATUS = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, status);
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String purchaser = resultSet.getString("PURCHASER");
            String tempStatus = resultSet.getString("STATUS");
            Timestamp date = resultSet.getTimestamp("CREATED_AT");
            MgtOrder mgtOrder = new MgtOrder(id, purchaser, tempStatus, date);
            mgtOrders.add(mgtOrder);
        }

        if (pstmt != null)
            pstmt.close();
        if (conn != null)
            conn.close();

        return mgtOrders;
    }


    public boolean insertList(ArrayList<Integer> sellectNum, String status) throws SQLException {
        PreparedStatement pstmt = null;
        int flag = 0;
        conn = open();
        String sql = "UPDATE mgt_orders SET STATUS = ? WHERE id = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, status);

        for (int num : sellectNum) {
            pstmt.setInt(2, num);
            pstmt.executeUpdate();
        }

        flag = pstmt.executeUpdate();
        close(conn);

        if (pstmt != null)
            pstmt.close();
        if (conn != null)
            conn.close();

        return flag > 0 ? true : false;
    }


    public ArrayList<MgtOrder> searchForDate(String date) throws SQLException {
        PreparedStatement pstmt = null;
        ArrayList<MgtOrder> mgtOrders = new ArrayList<>();
        conn = open();
        String sql = "SELECT * FROM mgt_orders " +
                "WHERE STATUS != ? " +
                "AND CREATED_AT " +
                "BETWEEN ? and ?+0.999999999";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "DELIVERED");
        pstmt.setString(2, date);
        pstmt.setString(3, date);
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String purchaser = resultSet.getString("PURCHASER");
            String tempStatus = resultSet.getString("STATUS");
            Timestamp tempDate = resultSet.getTimestamp("CREATED_AT");
            MgtOrder mgtOrder = new MgtOrder(id, purchaser, tempStatus, tempDate);
            mgtOrders.add(mgtOrder);
        }

        if (pstmt != null)
            pstmt.close();
        if (conn != null)
            conn.close();

        return mgtOrders;
    }


    public boolean delete(int orderId) throws SQLException {
        PreparedStatement pstmt = null;
        int flag = 0;
        conn = open();
        String sql = "DELETE FROM mgt_orders WHERE ID = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, orderId);
        flag = pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();
        if (conn != null)
            conn.close();

        return flag > 0 ? true : false;
    }
}
