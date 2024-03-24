package com.ssg.wsmt.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ssg.wsmt.product.domain.BrandVO;

@Mapper
public interface BrandMapper {
    BrandVO selectBrandID(int id);
    String getBrandName(int brandCodeId);
    List<String> getAllBrands();
}