package com.ssg.wsmt.inventory.service.impl;

import com.ssg.wsmt.inventory.domain.WarehouseVO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.dto.WarehouseDTO;
import com.ssg.wsmt.inventory.mapper.WarehouseMapper;
import com.ssg.wsmt.inventory.service.WarehouseSectionService;
import com.ssg.wsmt.inventory.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseMapper warehouseMapper;
    private final ModelMapper modelMapper;
    private final WarehouseSectionService warehouseSectionService;

    @Override
    @Transactional
    public void createWarehouse(WarehouseDTO warehouseDTO) {
        WarehouseVO warehouseVO = WarehouseVO.builder()
                .deliveryId(warehouseDTO.getDeliveryId())
                .type(warehouseDTO.getType())
                .id(warehouseDTO.getId())
                .location(warehouseDTO.getLocation())
                .build();
        warehouseMapper.createWarehouse(warehouseVO);

        // 창고 구역 생성
        warehouseSectionService.createWarehouseSection(warehouseVO);
    }

    @Override
    public WarehouseVO updateWarehouse() {
//        List<WarehouseVO> warehouseVOList = warehouseDao.readWarehouse();
//        WarehouseVO warehouseVO = new WarehouseVO();
//
//        try {
//            System.out.println("=".repeat(50));
//            System.out.printf("%-1s | %-4s | %-4s | %-4s\n", "창고번호", "택배사번호", "창고타입", "지역");
//            System.out.println("=".repeat(50));
//            for (WarehouseVO w : warehouseVOList) {
//                System.out.printf("%-7d | %-7d | %-6s | %-4s\n", w.getId(), w.getDelivery_id(), w.getType(), w.getLocation());
//            }
//            System.out.println("=".repeat(50));
//            System.out.println("택배사를 변경하고싶은 창고의 ID를 입력해주세요");
//            Long id = Long.parseLong(input.readLine());
//            for (WarehouseVO w : warehouseVOList) {
//                if (id == w.getId()) {
//                    warehouseVO.setLocation(w.getLocation());
//                    warehouseVO.setType(w.getType());
//                    warehouseVO.setId(w.getId());
//                }
//            }
//            deliveryUpdateWarehouse(warehouseVO);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return warehouseVO;
        return null;
    }

    @Override
    public WarehouseVO allUpdateWarehouse(WarehouseVO warehouseVO) {
        return null;
    }

    @Override
    public WarehouseVO deliveryUpdateWarehouse(WarehouseVO warehouseVO) {
//        Long chooseDcId = deliveryCmpService.chooseDeliveryCmp();
//        warehouseVO.setDelivery_id(chooseDcId);
//        warehouseDao.updateDeliveryCmpId(warehouseVO);
//        return warehouseVO;
        return null;
    }

    @Override
    public PageResponseDTO<WarehouseDTO> readAllWarehouseWithSection(PageRequestDTO pageRequestDTO) {
        // WarehouseMapper를 사용하여 페이지 요청에 따른 창고 정보 및 섹션 정보를 조회하는 메서드입니다.
        List<WarehouseDTO> dtoList = warehouseMapper.readWarehouseSection(pageRequestDTO);

        // 페이지 요청에 따른 전체 창고 수를 조회하는 메서드를 호출합니다.
        int total = warehouseMapper.getTotalCount(pageRequestDTO);

        // 페이지 응답 DTO를 생성하여 반환합니다.
        return PageResponseDTO.<WarehouseDTO>withAll()
                // 페이지 요청 DTO를 설정합니다.
                .pageRequestDTO(pageRequestDTO)
                // 조회된 창고 및 섹션 정보 DTO 리스트를 설정합니다.
                .dtoList(dtoList)
                // 전체 창고 수를 설정합니다.
                .total(total).build();
    }


    @Override
    public PageResponseDTO<WarehouseDTO> readAllWarehouse(PageRequestDTO pageRequestDTO) {
        List<WarehouseVO> voList = warehouseMapper.readWarehouse(pageRequestDTO);
        List<WarehouseDTO> warehouseDTOS = voList.stream()
                .map(vo -> modelMapper.map(vo, WarehouseDTO.class))
                .collect(Collectors.toList());
        int total = warehouseMapper.getTotalCount(pageRequestDTO);
        return PageResponseDTO.<WarehouseDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(warehouseDTOS)
                .total(total)
                .build();
    }

    @Override
    public void readOneWarehouse() {
//        Long id = 0l;
//
//        try {
//            System.out.println("창고의 ID를 입력해주세요");
//            id = Long.parseLong(input.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        WarehouseVO warehouseVO = warehouseDao.findById(id);
//        if (warehouseVO == null) {
//            System.out.println("없는 창고입니다.");
//            return;
//        }
//
//        System.out.println("=".repeat(50));
//        System.out.printf("%-1s | %-4s | %-4s | %-4s\n", "창고번호", "택배사번호", "창고타입", "지역");
//        System.out.println("=".repeat(50));
//        System.out.printf("%-7d | %-7d | %-6s | %-4s\n", warehouseVO.getId(), warehouseVO.getDelivery_id(), warehouseVO.getType(), warehouseVO.getLocation());
//        System.out.println("=".repeat(50));
//    }
    }
}
