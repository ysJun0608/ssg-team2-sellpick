package com.ssg.wsmt.mgtOrders.mapper;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface MgtOrdersMapper {
    Long createOrder(MgtOrders mgtOrders);

    void addItems(Long quantity, Long productId, Long mgtOrderId);


    Integer updateStatus(MgtOrdersStatus status, Long orderId);


    Integer cancelOrder(Long orderId);


    ArrayList<MgtOrders> selectAllForDate(String startDate, String endDate, String purchaser, String warehouseId);


    List<MgtOrders> searchOrders(String startDate, String endDate, String purchaser, String warehouseId);

    List<MgtOrders> searchOrdersAndStatus(String startDate, String endDate, String purchaser, String warehouseId, MgtOrdersStatus status);

    ArrayList<MgtOrders> searchForStatus(MgtOrdersStatus status);


    boolean insertList(String status, Long id);


    ArrayList<MgtOrders> searchNoneDelived(String date);


    Integer delete(Long orderId);
    Integer deleteItems(Long orderId);


    List<MgtOrders> selectAll();


    MgtOrders getOne(Long id);
}
