package com.ssg.wsmt.product.service.impl;

import com.ssg.wsmt.product.domain.ProductVO;
import com.ssg.wsmt.product.domain.ProductsVO;
import com.ssg.wsmt.product.dto.ProductsDTO;
import com.ssg.wsmt.product.enums.ProductsStatus;
import com.ssg.wsmt.product.mapper.ProductMapper;
import com.ssg.wsmt.product.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductMapper productMapper;
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    @Override
    public void register(ProductsDTO productsDTO) {
        ProductVO productVO = new ProductVO();
        productVO.setName(productsDTO.getName());
        productVO.setStatus(productsDTO.getStatus());
        productVO.setCost(productsDTO.getCost());
        productVO.setPrice(productsDTO.getPrice());
        productVO.setBrandId(Math.toIntExact(productsDTO.getBrandId()));
        productVO.setBusinessOwnerId(productsDTO.getBusinessOwnerId());


        productMapper.insert(productVO);
    }

    @Override
    public void modify(ProductsDTO productsDTO) {
        ProductVO productVO = new ProductVO();
        productVO.setStatus(productsDTO.getStatus());
        productVO.setPrice(productsDTO.getPrice());
        productVO.setPrice(productsDTO.getPrice());

        productMapper.update(productVO);
    }


    @Override
    public void productListInventory() {
        List<ProductsDTO> productList = productMapper.productListInventory();

        if (!productList.isEmpty()) {
            System.out.println("상품 리스트:");
            for (ProductsDTO product : productList) {
                System.out.print("상품 ID: " + product.getId());
                System.out.print(", 상품 이름: " + product.getName());
                System.out.print(", 판매 상태: " + product.getStatus().name());
                System.out.print(", 상품 원가: " + product.getCost());
                System.out.print(", 상품 판매가: " + product.getPrice());
                System.out.print(", 브랜드 id : " + product.getBrandId());
                System.out.println(", 사업자 id ; " + product.getBusinessOwnerId());
            }
            System.out.println("-".repeat(50));
        } else {
            System.out.println("상품이 없습니다.");
        }
    }

    private ProductsStatus isProductOnSale() throws IOException {
        while (true) {
            System.out.print("판매 중(Y) OR 판매 중지(N): ");
            String statusInput = br.readLine();
            if (statusInput.equalsIgnoreCase("Y")) {
                return ProductsStatus.ON_SALE;
            } else if (statusInput.equalsIgnoreCase("N")) {
                return ProductsStatus.STOP_SALE;
            } else {
                System.out.println("잘못된 입력입니다. Y 또는 N 중 하나를 입력하세요.");
            }
        }
    }
}

//    @Override
//    public void createProduct() throws IOException {
//        System.out.print("상품 이름: ");
//        String name = br.readLine();
//
//        ProductsStatus status = isProductOnSale();
//
//        System.out.print("상품 원가: ");
//        int cost = parseInt(br.readLine());
//
//        System.out.print("상품 판매가: ");
//        int price = parseInt(br.readLine());
//
//        System.out.print("브랜드 코드 ID: ");
//        Long brandId = Long.parseLong(br.readLine());
//
//        System.out.println("사업자 id");
//        Long ownerId = Long.parseLong(br.readLine());
//
//        ProductsVO product = new ProductsVO();
//        product.setName(name);
//        product.setStatus(status);
//        product.setCost(cost);
//        product.setPrice(price);
//        product.setBrandId(brandId);
//        product.setBusinessOwnerId(ownerId);
//
//        productMapper.createProduct(product);
//    }

//    @Override
//    public void modify(ProductsDTO productsDTO){
//        productListInventory();
//        System.out.print("업데이트할 상품의 id를 입력하세요: ");
//        long id = Integer.parseInt(br.readLine());
//
//        System.out.print("상품 이름: ");
//        String name = br.readLine();
//
//        System.out.print("상품 원가: ");
//        int cost = parseInt(br.readLine());
//
//        System.out.print("상품 판매가: ");
//        int price = parseInt(br.readLine());
//
//        ProductsVO product = new ProductsVO();
//        product.setId(id);
//        product.setName(name);
//        product.setCost(cost);
//        product.setPrice(price);
//        productMapper.updateProduct(product);
//    }
//
//}