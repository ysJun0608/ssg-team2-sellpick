package com.ssg.wsmt.mgtOrders.service;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;

import java.util.ArrayList;
import java.util.List;

public interface MgtOrdersService {
    Long createForm(MgtOrdersDTO mgtOrdersDTO);
    void addItems(Long quantities, Long productId, Long id);
    void getAllOrders();
    boolean confirmOrder();
    boolean cancelOrder();
    void confirmList();
    void searchNonDelivered();
    void confirmArrival();
    void delete(Long id);
    void deleteItems(Long id);

    List<MgtOrdersDTO> searchOrders(String startDate, String endDate, String purchaser, String warehouseId);

    List<MgtOrdersDTO> selectAll();
    MgtOrdersDTO getOne(Long id);
}