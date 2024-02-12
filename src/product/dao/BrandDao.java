package product.dao;

import DBIO.ObjectDBIO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandDao extends ObjectDBIO {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sqldb?serverTimezone=Asia/Seoul";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin1234";

    public String getBrandName(int brandCodeId) {
        Connection conn = open();
        String brandName = null;

        try {
            String sql = "SELECT name FROM brand WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, brandCodeId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                brandName = rs.getString("name");
            }

            rs.close();
            pstmt.close();
            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brandName;
    }

    public List<String> getAllBrands() {
        Connection conn = getConnection();
        List<String> brand = new ArrayList<>();

        try {
            String sql = "SELECT name FROM brand";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                brand.add(rs.getString("name"));
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brand;
    }


}