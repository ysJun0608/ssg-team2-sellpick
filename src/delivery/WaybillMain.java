package delivery;

import delivery.dao.WaybillDao;
import delivery.service.WaybillService;
import delivery.service.impl.WaybillServiceImpl;


public class WaybillMain {
    public static void main(String[] args) {
        WaybillService waybillService = new WaybillServiceImpl();
        waybillService.insertWaybill();
        System.out.println("insertWaybill======================");

        System.out.println("selectWaybill======================");

        Long longTemp = 2l;
        waybillService.selectWaybill(longTemp);
    }
}
