package com.ssg.wsmt.product.mapper;

import com.ssg.wsmt.product.domain.ProductVO;
import com.ssg.wsmt.product.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 상품 조회
    ProductVO select(Long id);
    // 상품 리스트 전체 조회
    List<ProductVO> selectAll(String name);
    // 상품등록
    void insert(ProductVO productVO);
    // 상품 수정
    void update(ProductVO productVO);
    // 상품 삭제
    void delete(Long id);
}