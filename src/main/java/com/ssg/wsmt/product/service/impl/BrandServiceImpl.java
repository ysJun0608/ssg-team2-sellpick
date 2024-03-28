package com.ssg.wsmt.product.service.impl;

import com.ssg.wsmt.product.domain.BrandVO;
import com.ssg.wsmt.product.mapper.BrandMapper;
import com.ssg.wsmt.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    //    private Connection conn;
    @Autowired
    private final BrandMapper brandMapper;

    // BrandMapper를 통해 모든 브랜드를 가져옵니다.
    public List<BrandVO> getAllBrand() {
        List<String> brandNames = brandMapper.getAllBrands();

        // 가져온 브랜드 이름을 Brand 객체로 변환하여 반환합니다.
        return convertToBrandList(brandNames);
    }

    // 브랜드 이름 리스트를 Brand 객체 리스트로 변환하는 메서드
    private List<BrandVO> convertToBrandList(List<String> brandNames) {
        // 여기서 각 브랜드 이름을 Brand 객체로 변환하여 리스트에 추가하는 로직을 작성하세요.
        // 이 예시에서는 Brand 객체가 브랜드 이름만을 가지고 있다고 가정합니다.
        // 실제로는 Brand 객체가 브랜드의 다양한 정보를 가지고 있을 수 있습니다.
        // 이를 고려하여 적절한 변환 로직을 작성해주세요.
        // 아래는 더미로 각 브랜드 이름을 Brand 객체로 변환하는 코드입니다.

        List<BrandVO> brands = new ArrayList<>();
        for (String brandName : brandNames) {
            BrandVO brand = new BrandVO();
            brand.setName(brandName);
            brands.add(brand);
        }
        return brands;
    }
    @Override
    public String getBrandName(int brandCodeId) {
        // BrandMapper를 통해 브랜드 이름을 가져옵니다.
        return brandMapper.getBrandName(brandCodeId);
    }
}
