package com.ssg.wsmt.mgtOrders.service;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.DTO.PageRequestDTO;
import com.ssg.wsmt.mgtOrders.DTO.PageResponseDTO;
import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;

import java.util.List;

public interface MgtOrdersService {
    Long createForm(MgtOrdersDTO mgtOrdersDTO);
    void addItems(Long quantities, Long productId, Long id);
    void getAllOrders();
    boolean confirmOrder(Long orderId);
    boolean cancelOrder();
    void confirmList();
    void searchNonDelivered();
    void confirmArrival();
    void delete(Long id);
    void deleteItems(Long id);

    List<MgtOrdersDTO> searchForStatus(MgtOrdersStatus status);

    List<MgtOrdersDTO> searchOrdersAndStatus(String startDate, String endDate, String purchaser, String warehouseId, MgtOrdersStatus status);

    List<MgtOrdersDTO> searchOrders(String startDate, String endDate, String purchaser, String warehouseId);

    PageResponseDTO<MgtOrdersDTO> selectAll(PageRequestDTO pageRequestDTO);
    MgtOrdersDTO getOne(Long id);
}