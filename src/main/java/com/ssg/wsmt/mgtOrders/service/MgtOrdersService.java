package com.ssg.wsmt.mgtOrders.service;

import com.ssg.wsmt.inventory.domain.WarehouseVO;
import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.DTO.PageRequestDTO;
import com.ssg.wsmt.mgtOrders.DTO.PageResponseDTO;
import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import com.ssg.wsmt.product.domain.ProductVO;

import java.util.List;

public interface MgtOrdersService {
    Long createForm(MgtOrdersDTO mgtOrdersDTO);
    void addItems(Long quantities, Long productId, Long id);
    void getAllOrders();
    boolean confirmOrder(Long orderId);
    void delete(Long id);
    void deleteItems(Long id);

    List<MgtOrdersDTO> searchForStatus(MgtOrdersStatus status);

    PageResponseDTO<MgtOrdersDTO> searchOrdersAndStatus(PageRequestDTO pageRequestDTO);


    List<MgtOrdersDTO> searchOrders(String startDate, String endDate, String purchaser, String warehouseId);

    PageResponseDTO<MgtOrdersDTO> selectAll(PageRequestDTO pageRequestDTO);
    MgtOrdersDTO getOne(Long id);

    List<WarehouseVO> selectWarehouseList(); // warehouse 테이블의 모든 데이터 조회

    List<ProductVO> selectProductList(); // products 테이블의 모든 데이터 조회


}