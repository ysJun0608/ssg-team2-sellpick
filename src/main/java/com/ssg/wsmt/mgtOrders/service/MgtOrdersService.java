package com.ssg.wsmt.mgtOrders.service;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;

import java.util.List;

public interface MgtOrdersService {
    Long createForm(MgtOrdersDTO mgtOrdersDTO);
    void addItems();
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