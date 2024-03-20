package com.ssg.wsmt.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BrandMapper {
    String getBrandName(int brandCodeId);
    List<String> getAllBrands();
}
