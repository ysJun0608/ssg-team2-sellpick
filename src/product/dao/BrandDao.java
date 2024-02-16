package product.dao;

import DBIO.ObjectDBIO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandDao extends ObjectDBIO {
/*
    브랜드 코드 ID에 해당하는 브랜드 이름을 데이터베이스에서 조회합니다.


*/
    public String getBrandName(int brandCodeId) {
        Connection conn = open();
        String brandName = null;

        try {
            String sql = "SELECT NAME FROM BRAND WHERE ID = ?";
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
        Connection conn = open();
        List<String> brand = new ArrayList<>();



        return brand;
    }



}