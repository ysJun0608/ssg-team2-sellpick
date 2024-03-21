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
    public Long createOrder(MgtOrders mgtOrders);

    public boolean addItems(Long id);


    public Integer updateStatus(MgtOrdersStatus status, Long orderId);


    public Integer cancelOrder(Long orderId);


    public ArrayList<MgtOrders> selectAllForDate(String startDate, String endDate);


    public ArrayList<MgtOrders> searchForStatus(MgtOrdersStatus status);


    public boolean insertList(String status, Long id);


    public ArrayList<MgtOrders> searchNoneDelived(String date);


    public Integer delete(Long orderId);


    List<MgtOrders> selectAll();


    MgtOrders getOne(Long id);
}
