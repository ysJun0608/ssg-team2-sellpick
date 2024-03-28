package com.ssg.wsmt.inventory.dto;

import lombok.Data;

import java.util.List;

@Data
public class WarehouseCreateDTO {
    private String courier; // 택배사
    private String shop;    // 쇼핑몰
    private int sectionCount; // 창고 섹션 개수
    private List<String> sectionTypes; // 창고 섹션 타입들
    private String warehouseLocation; // 창고 주소

    // 생성자, Getter, Setter 등을 작성합니다.
}