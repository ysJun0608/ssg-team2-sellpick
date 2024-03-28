package com.ssg.wsmt.inventory.service.impl;

import com.ssg.wsmt.inventory.domain.WarehouseVO;
import com.ssg.wsmt.inventory.mapper.WarehouseMapper;
import com.ssg.wsmt.inventory.service.WarehouseSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseSectionServiceImpl implements WarehouseSectionService {

    private final WarehouseMapper warehouseMapper;

    @Override
    public void createWarehouseSection(WarehouseVO warehouseVO) {
//                WarehouseSectionVO warehouseSectionVO = WarehouseSectionVO.builder()
//                        .warehouseId(warehouseVO.getId())
//                        .type(warehouseVO.getType(
//                        .name(warehouseVO.getType()+warehouseVO.getId())
//                        .build();
//
//
//        WarehouseSectionVO section = new WarehouseSectionVO();
//        section.setWarehouseId(warehouseVO.getId());
//        try {
//            Long countSection = null;
//            while (true) {
//                System.out.println("창고 구역을 몇개 생성할지 정해주세요(최대 4개)");
//                System.out.println("=".repeat(50));
//                countSection = Long.parseLong(input.readLine());
//                if (countSection > 4) {
//
//                    System.out.println("최대 구역은 4개입니다. 다시 입력해주세요");
//                    System.out.println("=".repeat(50));
//                    continue;
//                }
//
//                System.out.println(countSection + "개의 구역을 생성하겠습니다.");
//                System.out.println("=".repeat(50));
//                if (countSection <= 4) break;
//            }
//
//            System.out.println("각 구역의 세부타입메뉴를 정하시오");
//
//            System.out.println("1. 냉장 | 2. 냉동 | 3. 건조 | 4. 가공");
//            System.out.println("=".repeat(50));
//
//
//            for (int i = 0; i < countSection; i++) {
//                System.out.println("구역이름은 타입 뒤에 창고ID로 붙습니다.");
//                System.out.println("=".repeat(50));
//                String num = input.readLine();
//                switch (num) {
//                    case "1" -> {
//
//                        section.setName("냉장" + " " + warehouseVO.getId());
//                        section.setType(WhSectionType.REFRIGERATED);
//                        boolean ack = warehouseSectionDao.saveWarehouseSection(section);
//                        if (ack == true) System.out.println("냉장구역이 추가되었습니다.");
//                        System.out.println("=".repeat(50));
//                    }
//                    case "2" -> {
//
//                        section.setName("냉동" + " " + warehouseVO.getId());
//                        section.setType(WhSectionType.FROZEN);
//                        boolean ack = warehouseSectionDao.saveWarehouseSection(section);
//                        if (ack == true) System.out.println("냉동구역이 추가되었습니다.");
//                        System.out.println("=".repeat(50));}
//                    case "3" -> {
//                        section.setName("건조" + " " + warehouseVO.getId());
//                        section.setType(WhSectionType.DRY);
//                        boolean ack = warehouseSectionDao.saveWarehouseSection(section);
//                        if (ack == true) System.out.println("건조구역이 추가되었습니다.");
//                        System.out.println("=".repeat(50));}
//                    case "4" -> {
//                        section.setName("가공" + " " + warehouseVO.getId());
//                        section.setType(WhSectionType.PROCESSED);
//                        boolean ack = warehouseSectionDao.saveWarehouseSection(section);
//                        if (ack == true) System.out.println("가공구역이 추가되었습니다.");
//                        System.out.println("=".repeat(50));}
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
