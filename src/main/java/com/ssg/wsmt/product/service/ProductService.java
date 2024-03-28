package com.ssg.wsmt.product.service;

import com.ssg.wsmt.product.domain.BrandVO;
import com.ssg.wsmt.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    // 상품등록
    void register(ProductDTO productDTO);

    // 상품 조회
    ProductDTO getOne(long id);

    // 상품 리스트 전체 조회
    List<ProductDTO> getAll(String name);

    // 상품 수정
    void modify(ProductDTO productDTO);
    // 상품 삭제
    void remove(long id);

    BrandVO getBrandById(int id);
}
