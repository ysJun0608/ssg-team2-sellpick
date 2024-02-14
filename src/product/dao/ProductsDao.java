package product.dao;

import DBIO.ObjectDBIO;
import product.domain.Products;
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

    public List<Products> productListInventory() {
        open();
        List<Products> productList = new ArrayList<>();

        if (conn != null) {
            try {
                String sql = "SELECT p.id, p.name, p.status, p.cost, p.price, i.quantity, ws.NAME AS warehouse_section_name, w.LOCATION AS warehouse_location " +
                        "FROM products p " +
                        "LEFT JOIN inventory i ON p.id = i.PRODUCTS_ID " +
                        "LEFT JOIN warehouse_section ws ON i.WH_SECTION_ID = ws.ID " +
                        "LEFT JOIN warehouse w ON ws.WAREHOUSE_ID = w.ID";

                        ;
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    long productId = rs.getInt("id");
                    String productName = rs.getString("name");
                    String productStatus = rs.getString("status");
                    int productCost = rs.getInt("cost");
                    int productPrice = rs.getInt("price");
                    int productQuantity = rs.getInt("quantity");
                    String warehouseSectionName = rs.getString("warehouse_section_name");
                    String warehouseLocation = rs.getString("warehouse_location");

                    Products product = new Products();
                    product.setId(productId);
                    product.setName(productName);
                    product.setStatus(product.isStatus());
                    product.setCost(productCost);
                    product.setPrice(productPrice);
                    product.setQuantity(productQuantity);
                    product.setWarehouseSectionName(warehouseSectionName);
                    product.setWarehouseLocation(warehouseLocation);
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
                String sql = "INSERT INTO products (name, status, cost, price, brand_Id, owner_Id) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, product.getName());
                pstmt.setBoolean(2, product.isStatus());
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
                String sql = "UPDATE products SET name = ?, cost = ?, price = ? WHERE id = ?";
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
    public static void main(String[] args) {
        // ProductsServiceImpl 인스턴스 생성
        ProductsService productService = new ProductsServiceImpl();

       /*    // 상품 목록 조회
           productService.productListInventory();

       // 새 상품 생성
   productService.createProduct();*/

        // 업데이트할 상품 정보 입력 후 업데이트
           productService.updateProduct();

      /*      // 업데이트된 상품 목록 조회
            productService.productListInventory();*/
    }
}





