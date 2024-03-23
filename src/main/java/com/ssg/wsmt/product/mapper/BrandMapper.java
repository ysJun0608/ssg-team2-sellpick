package com.ssg.wsmt.product.mapper;

import com.ssg.wsmt.product.domain.BrandVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandMapper {
    BrandVO selectBrandID(int id);
}
