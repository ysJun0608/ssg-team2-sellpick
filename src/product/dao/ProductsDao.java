package product.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductsDao {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sqldb?serverTimezone=Asia/Seoul";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin1234";

    private Connection conn = null;

    public void connect() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 상품 상태 T/F
    public boolean readProductStatus(BufferedReader br) throws IOException {
        System.out.print("상품 상태 (T/F): ");
        String statusInput = br.readLine();
        boolean status;
        if (statusInput.equalsIgnoreCase("T")) {
            status = true;
        } else if (statusInput.equalsIgnoreCase("F")) {
            status = false;
        } else {
            System.out.println("잘못된 입력입니다. T 또는 F 중 하나를 입력하세요.");
            throw new IllegalArgumentException("잘못된 입력입니다. T 또는 F 중 하나를 입력하세요.");
        }
        return status;
    }

    public void createProduct() {
        connect();

        if (conn != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("상품 id: ");
                int id = Integer.parseInt(br.readLine());
                boolean status = readProductStatus(br); // 상태 입력 메서드 호출
                System.out.print("상품 원가: ");
                Integer cost = Integer.parseInt(br.readLine());
                System.out.print("상품 수량: ");
                int quantity = Integer.parseInt(br.readLine());
                System.out.print("상품 판매가: ");
                int price = Integer.parseInt(br.readLine());

                System.out.print("브랜드 코드 ID: ");
                Integer brandCodeId = Integer.parseInt(br.readLine());
                BrandDao brandDao = new BrandDao();
                String brandName = brandDao.getBrandName(brandCodeId);
                System.out.println("선택한 브랜드: " + brandName);

                System.out.print("소유자 ID: ");
                int ownerId = Integer.parseInt(br.readLine());
                System.out.print("창고 ID: ");
                int warehouseId = Integer.parseInt(br.readLine());

                String sql = "INSERT INTO products (id, status, cost, quantity, price, brandCode_id, Owner_id, warehouse_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.setBoolean(2, status);
                pstmt.setObject(3, cost);
                pstmt.setInt(4, quantity);
                pstmt.setInt(5, price);
                pstmt.setObject(6, brandCodeId);
                pstmt.setInt(7, ownerId);
                pstmt.setInt(8, warehouseId);

                pstmt.executeUpdate();
                pstmt.close();
                br.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("데이터베이스 연결 실패");
        }
    }

    public void updateProduct() {
        connect();

        if (conn != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("업데이트할 상품의 id를 입력하세요: ");
                int id = Integer.parseInt(br.readLine());

                // 새로운 상품 정보 입력
                System.out.println("새로운 상품 정보 입력:");
                boolean status = readProductStatus(br);
                System.out.print("상품 원가: ");
                Integer cost = Integer.parseInt(br.readLine());
                System.out.print("상품 수량: ");
                int quantity = Integer.parseInt(br.readLine());
                System.out.print("상품 판매가: ");
                int price = Integer.parseInt(br.readLine());
                System.out.print("브랜드 코드 ID: ");
                Integer brandCodeId = Integer.parseInt(br.readLine());
                BrandDao brandDao = new BrandDao();
                String brandName = brandDao.getBrandName(brandCodeId);
                System.out.println("선택한 브랜드: " + brandName);

                System.out.print("소유자 ID: ");
                int ownerId = Integer.parseInt(br.readLine());
                System.out.print("창고 ID: ");
                int warehouseId = Integer.parseInt(br.readLine());

                // 업데이트
                String sql = "UPDATE products SET status = ?, cost = ?, quantity = ?, price = ?, brandCode_id = ?, Owner_id = ?, warehouse_id = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setBoolean(1, status);
                pstmt.setObject(2, cost);
                pstmt.setInt(3, quantity);
                pstmt.setInt(4, price);
                pstmt.setObject(5, brandCodeId);
                pstmt.setInt(6, ownerId);
                pstmt.setInt(7, warehouseId);
                pstmt.setInt(8, id);

                pstmt.executeUpdate();
                pstmt.close();
                br.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("데이터베이스 연결 실패");
        }
    }


    public static void main(String[] args) {
        ProductsDao productsDao = new ProductsDao();
        productsDao.createProduct();
        /*productsDao.updateProduct();*/
    }
}