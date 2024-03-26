package com.ssg.wsmt.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ssg.wsmt.product.domain.BrandVO;

import java.util.List;

@Mapper
public interface BrandMapper {
    BrandVO selectBrandID(int id);
    String getBrandName(int brandCodeId);
    List<String> getAllBrands();
}