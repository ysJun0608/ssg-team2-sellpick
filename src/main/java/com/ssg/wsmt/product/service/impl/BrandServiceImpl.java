package com.ssg.wsmt.product.service.impl;

import com.ssg.wsmt.product.domain.BrandVO;
import com.ssg.wsmt.product.mapper.BrandMapper;
import com.ssg.wsmt.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private Connection conn;
    private final BrandMapper brandMapper;

//    public BrandServiceImpl(Connection conn) {
//        this.conn = conn;
//    }

    @Override
    public List<BrandVO> getAllBrand() {
        List<BrandVO> brandList = new ArrayList<>();
        try {
            String sql = "SELECT name FROM brand";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BrandVO brand = new BrandVO();
                brand.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
                brandList.add(brand);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandList;
    }
}