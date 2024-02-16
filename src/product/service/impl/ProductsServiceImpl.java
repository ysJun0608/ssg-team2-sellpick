package product.service.impl;

import product.dao.ProductsDao;
import product.domain.Products;
import product.dto.ProductsOutput;
import product.enums.ProductsStatus;
import product.service.ProductsService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ProductsServiceImpl implements ProductsService {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ProductsDao productsDao;

    public ProductsServiceImpl() {
        this.productsDao = new ProductsDao();
    }

    @Override
    public void productListInventory() {
        List<ProductsOutput> productList = productsDao.productListInventory();

        if (!productList.isEmpty()) {
            System.out.println("상품 리스트:");
            for (ProductsOutput product : productList) {
                System.out.print("상품 ID: " + product.id());
                System.out.print(", 상품 이름: " + product.name());
                System.out.print(", 판매 상태: " + product.status().name());
                System.out.print(", 상품 원가: " + product.cost());
                System.out.print(", 상품 판매가: " + product.price());
                System.out.print(", 브랜드 id : " + product.brandId());
                System.out.print(", 브랜드 이름 : " + product.brandName());
                System.out.println(", 사업자 id ; " + product.businessOwnerId());
            }
            System.out.println("-".repeat(50));
        } else {
            System.out.println("상품이 없습니다.");
        }
    }

    private ProductsStatus isProductOnSale() {
        while (true) {
            try {
                System.out.print("판매 중(Y) OR 판매 중지(N): ");
                String statusInput = br.readLine();
                if (statusInput.equalsIgnoreCase("Y")) {
                    return ProductsStatus.ON_SALE;
                } else if (statusInput.equalsIgnoreCase("N")) {
                    return ProductsStatus.STOP_SALE;
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

            System.out.print("상품 이름: ");
            String name = br.readLine();

            ProductsStatus status = isProductOnSale();

            System.out.print("상품 원가: ");
            int cost = parseInt(br.readLine());

            System.out.print("상품 판매가: ");
            int price = parseInt(br.readLine());

            System.out.print("브랜드 코드 ID: ");
            Long brandId = Long.parseLong(br.readLine());

            System.out.println("사업자 id");
            Long ownerId = Long.parseLong(br.readLine());

            Products product = new Products();
            product.setName(name);
            product.setStatus(status);
            product.setCost(cost);
            product.setPrice(price);
            product.setBrandId(brandId);
            product.setBusinessOwnerId(ownerId);

            productsDao.createProduct(product);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct() {
        productListInventory();
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


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}