package product.service.impl;

import product.dao.ProductsDao;
import product.domain.Products;
import product.service.ProductsService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ProductsServiceImpl implements ProductsService {
    private ProductsDao productsDao;

    public ProductsServiceImpl() {
        this.productsDao = new ProductsDao();
    }

    @Override
    public void productListInventory() {
        List<Products> productList = productsDao.productListInventory();

        if (!productList.isEmpty()) {
            System.out.println("상품 리스트:");
            for (Products product : productList) {
                System.out.println("상품 ID: " + product.getId());
                System.out.println("상품 이름: " + product.getName());
                System.out.println("판매 상태: " + (product.isStatus() ? "판매 중" : "판매 중지"));
                System.out.println("상품 원가: " + product.getCost());
                System.out.println("상품 판매가: " + product.getPrice());
                System.out.println("상품 재고: " + product.getQuantity());
                //System.out.println("창고 구역 이름: " + product.getWarehouseSectionName());
                String a = product.getWarehouseSectionName();
                String b = product.getWarehouseLocation();
                if (a==null&&b==null) {
                    a = "현재 재고가 없습니다";
                    b = "현재 재고가 없습니다";
                }
                System.out.println("창고 구역 이름: " + a);

                System.out.println("창고 위치: " + b);


                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("상품이 없습니다.");
        }
    }

    @Override
    public boolean isProductOnSale(BufferedReader br) {
        while (true) {
            try {
                System.out.print("판매 중(Y) OR 판매 중지(N): ");
                String statusInput = br.readLine();
                if (statusInput.equalsIgnoreCase("Y")) {
                    return true;
                } else if (statusInput.equalsIgnoreCase("N")) {
                    return false;
                } else {
                    System.out.println("잘못된 입력입니다. Y 또는 N 중 하나를 입력하세요.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createProduct() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("상품 id: ");
            long id = parseInt(br.readLine());

            System.out.print("상품 이름: ");
            String name = br.readLine();

            boolean status = isProductOnSale(br);

            System.out.print("상품 원가: ");
            int cost = parseInt(br.readLine());

            System.out.print("재고 개수: ");
            int  quantity = Integer.parseInt(br.readLine());

            System.out.print("상품 판매가: ");
            int price = parseInt(br.readLine());

            System.out.print("브랜드 코드 ID: ");
            Long brandId = Long.parseLong(br.readLine());

            System.out.println("사업자 id");
            Long ownerId = Long.parseLong(br.readLine());

            Products product = new Products();
            product.setId(id);
            product.setName(name);
            product.setStatus(status);
            product.setCost(cost);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setBrandId(brandId);
            product.setBusinessOwnerId(ownerId);

            productsDao.createProduct(product);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("업데이트할 상품의 id를 입력하세요: ");
            long id = Integer.parseInt(br.readLine());

            System.out.print("상품 이름: ");
            String name = br.readLine();

            System.out.print("상품 원가: ");
            int cost = parseInt(br.readLine());

            System.out.print("상품 판매가: ");
            int price = parseInt(br.readLine());

            Products product = new Products();
            product.setId(id);
            product.setName(name);
            product.setCost(cost);
            product.setPrice(price);
            productsDao.updateProduct(product);

            productListInventory();

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}