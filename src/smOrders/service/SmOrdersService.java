package smOrders.service;


import smOrders.domain.smOrders;
import smOrders.dto.SmOrdersAllOutput;

import java.util.List;

public interface SmOrdersService {
    /**
     * 주문을 생성합니다.
     */
    void createOrder();

    /**
     * 주문을 조회합니다.
     */
    void readOrder();
//void readOrder(SMOrders smorders); // SMOrders 객체를 이용하여 주문을 조회합니다.

    /**
     * 취소된 주문을 조회합니다.
     *
     * @return 취소된 주문 목록
     */
    List<smOrders> readAllCanceledOrders();

    /**
     * 배송준비중인 주문을 조회합니다.
     *
     * @return 배송준비중인 주문 목록
     */
    List<smOrders> readAllPreparedOrders();

    /**
     * 주문 상태를 업데이트합니다.
     */
    void updateStatus();

    /**
     * 주문을 한 건 조회합니다.
     *
     * @return 조회된 주문 객체
     */
    smOrders readOne();

     /** 주문을 한 건 조회합니다.
            *
            * @return 조회된 주문 객체
     */
    SmOrdersAllOutput readOneSelect();
}