package com.ssg.wsmt.product.service;

import com.ssg.wsmt.product.domain.BrandVO;

import java.util.List;

public interface BrandService {
    List<BrandVO> getAllBrand();
    String getBrandName(int brandCodeId);

}
