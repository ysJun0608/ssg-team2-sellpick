package delivery;

import delivery.dao.WaybillDao;


public class WaybillMain {
    public static void main(String[] args) {
        WaybillDao waybillDao = new WaybillDao();
        System.out.println("insertWaybill======================");
        Long ordersNum = waybillDao.insertWaybill();

        System.out.println("selectWaybill======================");
        waybillDao.selectWaybill(ordersNum);
    }
}
