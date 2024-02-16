package product.dao;

import DBIO.ObjectDBIO;
import product.domain.Products;
import product.dto.ProductsOutput;
import product.enums.ProductsStatus;
import product.service.ProductsService;
import product.service.impl.ProductsServiceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class ProductsDao extends ObjectDBIO {
    Connection conn = open();

    public List<ProductsOutput> productListInventory() {
        open();
        List<ProductsOutput> productList = new ArrayList<>();

        if (conn != null) {
            try {
                String sql = "SELECT P.ID, P.NAME, P.STATUS, P.PRICE, P.COST, P.BRAND_ID, P.OWNER_ID," +
                        " B.NAME AS BRAND_NAME " +
                        "FROM PRODUCTS P " +
                        "JOIN BRAND B " +
                        "ON P.BRAND_ID = B.ID " +
                        "ORDER BY P.ID";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    long productId = rs.getInt("id");
                    String productName = rs.getString("name");
                    String productStatus = rs.getString("status");
                    int productCost = rs.getInt("cost");
                    int productPrice = rs.getInt("price");
                    Long brandId = rs.getLong("brand_id");
                    Long ownerId = rs.getLong("owner_id");
                    String brandName = rs.getString("brand_name");

                    ProductsOutput product = ProductsOutput.builder()
                            .id(productId)
                            .name(productName)
                            .brandId(brandId)
                            .brandName(brandName)
                            .cost(productCost)
                            .price(productPrice)
                            .status(ProductsStatus.valueOf(productStatus))
                            .businessOwnerId(ownerId)
                            .build();

                    productList.add(product);
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close(conn);
            }
        } else {
            System.out.println("데이터베이스 연결 실패");
        }

        return productList;
    }


    public void createProduct(Products product) {
        open();

        if (conn != null) {
            try {
                String sql = "INSERT INTO PRODUCTS (NAME, STATUS, COST, PRICE, BRAND_ID, OWNER_ID) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, product.getName());
                pstmt.setString(2, product.getStatus().name());
                pstmt.setInt(3, product.getCost());
                pstmt.setInt(4, product.getPrice());
                pstmt.setLong(5, product.getBrandId());
                pstmt.setLong(6, product.getBusinessOwnerId());

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating product failed, no rows affected.");
                }

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long id = generatedKeys.getLong(1);
                        product.setId(id); // 생성된 ID를 상품 객체에 설정
                    } else {
                        throw new SQLException("Creating product failed, no ID obtained.");
                    }
                }
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close(conn);
            }
        } else {
            System.out.println("데이터베이스 연결 실패");
        }
    }
    public void updateProduct(Products product) {
        open();

        if (conn != null) {
            try {
                String sql = "UPDATE PRODUCTS SET NAME = ?, COST = ?, PRICE = ? WHERE ID = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, product.getName());
                pstmt.setInt(2, product.getCost());
                pstmt.setInt(3, product.getPrice());

                pstmt.setLong(4, product.getId());

                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close(conn);
            }
        } else {
            System.out.println("데이터베이스 연결 실패");
        }
    }
}





