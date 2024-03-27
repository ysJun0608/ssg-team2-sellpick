package com.ssg.wsmt.product.service.impl;

import com.ssg.wsmt.product.domain.BrandVO;
import com.ssg.wsmt.product.domain.ProductVO;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.mapper.BrandMapper;
import com.ssg.wsmt.product.mapper.ProductMapper;
import com.ssg.wsmt.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final BrandMapper brandMapper;

    // 상품 등록
    @Override
    public void register(ProductDTO productDTO) {
        ProductVO productVO = ProductVO.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .status(productDTO.getStatus())
                .cost(productDTO.getCost())
                .price(productDTO.getPrice())
                .brandId(productDTO.getBrandId())
                .businessOwnerId(productDTO.getBusinessOwnerId())
                .build();
        productMapper.insert(productVO);
    }

    // 상품 조회
    @Override
    public ProductDTO getOne(long id) {
        ProductVO productVO = productMapper.select(id);
        ProductDTO productDTO = ProductDTO.builder()
                .id(productVO.getId())
                .name(productVO.getName())
                .status(productVO.getStatus())
                .cost(productVO.getCost())
                .price(productVO.getPrice())
                .brandId(productVO.getBrandId())
                .businessOwnerId(productVO.getBusinessOwnerId())
                .build();
        return productDTO;
    }

    // 상품 리스트 전체 조회
    @Override
    public List<ProductDTO> getAll(String name) {
        List<ProductVO> productList = productMapper.selectAll(name);
        log.info("select all실행 : "+ productList);


        List<ProductDTO> dtoList = productList.stream()
                .map(productVO -> {
                    log.info(productVO.getBrandId());
                    log.info(getBrandById(productVO.getBrandId()));
                    BrandVO brandVO = getBrandById(productVO.getBrandId());
                    if (brandVO == null) {
                        log.error("Brand not found for id: " + productVO.getBrandId());
                        return null; // 예시로 null 반환
                    }
                    return ProductDTO.builder()
                            .id(productVO.getId())
                            .name(productVO.getName())
                            .status(productVO.getStatus())
                            .cost(productVO.getCost())
                            .price(productVO.getPrice())
                            .brandId(productVO.getBrandId())
                            .brandName(brandVO.getName())
                            .businessOwnerId(productVO.getBusinessOwnerId())
                            .build();
                })
                .filter(Objects::nonNull) // null이 아닌 객체만 필터링
                .collect(Collectors.toList());
        return dtoList;
    }


    // 상품 수정
    @Override
    public void modify(ProductDTO productDTO) {
        ProductVO productVO = ProductVO.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .status(productDTO.getStatus())
                .cost(productDTO.getCost())
                .price(productDTO.getPrice())
                .brandId(productDTO.getBrandId())
                .businessOwnerId(productDTO.getBusinessOwnerId())
                .build();
        productMapper.update(productVO);

        log.info("모디파이 수정중이야");
        log.info(productVO);
    }

    // 상품 삭제
    @Override
    public void remove(long id) {
        productMapper.delete(id);
    }

    // 브랜드 정보 조회
    @Override
    public BrandVO getBrandById(int id) {
        return brandMapper.selectBrandID(id);
    }
}