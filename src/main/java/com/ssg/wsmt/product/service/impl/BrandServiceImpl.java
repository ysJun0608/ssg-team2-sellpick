package com.ssg.wsmt.product.service.impl;

import com.ssg.wsmt.product.domain.Brand;
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
    public List<Brand> getAllBrand() {
        List<Brand> brandList = new ArrayList<>();
        try {
            String sql = "SELECT name FROM brand";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Brand brand = new Brand();
                brand.setId(rs.getLong("id"));
                brand.setName(rs.getString("name"));
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