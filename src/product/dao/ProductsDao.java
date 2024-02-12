package product.dao;
import DBIO.ObjectDBIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductsDao extends ObjectDBIO {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sellpick?serverTimezone=Asia/Seoul&useLegacyDatetimeCode=false";
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

    // 상품 상태 Y/N
    public boolean readProductStatus(BufferedReader br) throws IOException {
        System.out.print("판매 중(Y) OR 판매 중지(N) ");
        String statusInput = br.readLine();
        boolean status;
        if (statusInput.equalsIgnoreCase("Y")) {
            status = true;
        } else if (statusInput.equalsIgnoreCase("N")) {
            status = false;
        } else {
            System.out.println("잘못된 입력입니다. Y 또는 N 중 하나를 입력하세요.");
            throw new IllegalArgumentException("잘못된 입력입니다. Y 또는 N 중 하나를 입력하세요.");
        }
        return status;
    }

   /* 상품을 데이터베이스에 추가합니다.
    상품 정보를 입력받고, 입력받은 정보를 데이터베이스에 저장합니다.*/
    public void createProduct() {
        connect();

        if (conn != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("상품 id: ");
                int id = Integer.parseInt(br.readLine());

                System.out.print("상품 이름: ");
                String name = br.readLine();

                boolean status = readProductStatus(br); // 상태 입력 메서드 호출
                System.out.print("상품 원가: ");
                Integer cost = Integer.parseInt(br.readLine());

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

                String sql = "INSERT INTO products (id, name, status, price, cost, brand_Id, owner_Id, warehouse_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setBoolean(3, status);
                pstmt.setInt(4, price);
                pstmt.setObject(5, cost);
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

    /*상품 정보를 업데이트합니다.
    업데이트할 상품의 ID 입력받아 새로운 상품 정보를 입력하고, 입력받은 정보를 데이터베이스에 업데이트합니다.*/
    public void updateProduct() {
        connect();

        if (conn != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("업데이트할 상품의 id를 입력하세요: ");
                int id = Integer.parseInt(br.readLine());

                // 새로운 상품 정보 입력
                System.out.print("상품 이름: ");
                String name = br.readLine();

                boolean status = readProductStatus(br); // 상태 입력 메서드 호출
                System.out.print("상품 원가: ");
                Integer cost = Integer.parseInt(br.readLine());

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
                String sql = "UPDATE products SET name =?, status = ?, price = ?,cost = ?, brand_Id = ?, owner_Id = ?, warehouse_Id = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, name);
                pstmt.setBoolean(2, status);
                pstmt.setInt(3, price);
                pstmt.setObject(4, cost);
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
                /*productsDao.createProduct();*/
                productsDao.updateProduct();
            }
}