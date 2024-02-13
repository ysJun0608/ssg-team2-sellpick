package product.dao;
import DBIO.ObjectDBIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductsDao extends ObjectDBIO {
    private Connection conn;
    public void connect() {
        conn = open();
    }
    /* 창고별 상품 수량 조회 */
    public void getProductQuantityByWarehouse(int warehouseId) {
        connect();

        if (conn != null) {
            try {
                String sql = "SELECT warehouse_id, COUNT(*) as quantity FROM products WHERE warehouse_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, warehouseId); // warehouseId 파라미터 설정
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    System.out.println("창고 ID: " + warehouseId + ", 상품 수량: " + quantity);
                }

                pstmt.close();
                rs.close();
            } catch (SQLException e) {
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
    // 상품 상태 Y/N
    private boolean isProductOnSale(BufferedReader br) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
            return false; // 예외 발생 시 기본적으로 상품 상태를 false로 설정
        }
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

                boolean status = isProductOnSale(br); // 상태 입력 메서드 호출
                System.out.print("상품 원가: ");
                Integer cost = Integer.parseInt(br.readLine());

                System.out.print("재고 개수: ");
                int quantity = Integer.parseInt(br.readLine());

                System.out.print("상품 판매가: ");
                int price = Integer.parseInt(br.readLine());

                BrandDao brandDao = new BrandDao();
                List<String> brandList = brandDao.getAllBrands(); // 브랜드 목록 가져오기
                // 브랜드 선택
                System.out.println("브랜드 목록:");
                for (int i = 0; i < brandList.size(); i++) {
                    System.out.println((i + 1) + ". " + brandList.get(i));
                }

                System.out.print("브랜드 코드 ID: ");
                Integer brandCodeId = Integer.parseInt(br.readLine());
                String brandName = brandDao.getBrandName(brandCodeId);
                System.out.println("선택한 브랜드: " + brandName);

                System.out.println("사업자 id");
                int ownerId = Integer.parseInt(br.readLine());

                String sql = "INSERT INTO products (id, name, status, cost, price, quantity, brand_id, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,id);
                pstmt.setString(2, name);
                pstmt.setBoolean(3, status);
                pstmt.setLong(4, cost);
                pstmt.setInt(5, price);
                pstmt.setInt(6,quantity);
                pstmt.setLong(7, brandCodeId);
                pstmt.setInt(8, ownerId);

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

                System.out.print("상품 원가: ");
                Integer cost = Integer.parseInt(br.readLine());

                System.out.print("상품 판매가: ");
                int price = Integer.parseInt(br.readLine());


                // 업데이트
                String sql = "UPDATE products SET price = ?,cost = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, price);
                pstmt.setLong(2, cost);
                pstmt.setInt(3, id);
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
/*
                productsDao.updateProduct();
*/
            }
}