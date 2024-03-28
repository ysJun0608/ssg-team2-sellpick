package com.ssg.wsmt.mgtOrders.mapper;

import com.ssg.wsmt.inventory.domain.WarehouseVO;
import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.DTO.PageRequestDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import com.ssg.wsmt.product.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

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

    List<MgtOrdersDTO> searchOrdersAndStatus(PageRequestDTO pageRequestDTO);

    ArrayList<MgtOrders> searchForStatus(MgtOrdersStatus status);


    boolean insertList(String status, Long id);


    ArrayList<MgtOrders> searchNoneDelived(String date);


    Integer delete(Long orderId);
    Integer deleteItems(Long orderId);


    List<MgtOrdersDTO> searchAll(PageRequestDTO pageRequestDTO);

    int getTotalCount(PageRequestDTO pageRequestDTO);

    MgtOrders getOne(Long id);

    List<WarehouseVO> selectWarehouseList(); // warehouse 테이블의 모든 데이터 조회

    List<ProductVO> selectProductList(); // products 테이블의 모든 데이터 조회
}
