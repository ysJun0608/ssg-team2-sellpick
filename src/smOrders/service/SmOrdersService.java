package smOrders.service;


import smOrders.domain.smOrders;

import java.util.List;

public interface SmOrdersService {
    void createOrder();

    void readOrder();
//    void readOrder(SMOrders smorders);

    List<smOrders> readAllCanceledOrders();

    List<smOrders> readAllPreparedOrders();

    void updateStatus();

    smOrders readOne();

}