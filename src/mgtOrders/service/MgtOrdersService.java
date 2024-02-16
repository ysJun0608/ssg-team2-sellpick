package mgtOrders.service;

public interface MgtOrdersService {
    void add();
    void delete();
    void getAllOrders();
    boolean confirmOrder();
    boolean cancelOrder();
    void confirmList();
    void searchNonDelivered();

    void confirmArrival();
}
