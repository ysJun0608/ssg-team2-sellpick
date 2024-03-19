package com.ssg.wsmt.mgtOrders.service;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;

import java.util.List;

public interface MgtOrdersService {
    void add(MgtOrdersDTO mgtOrdersDTO);
    void getAllOrders();
    boolean confirmOrder();
    boolean cancelOrder();
    void confirmList();
    void searchNonDelivered();
    void confirmArrival();
    void delete();

    List<MgtOrdersDTO> selectAll();
    MgtOrdersDTO getOne(Long id);
}