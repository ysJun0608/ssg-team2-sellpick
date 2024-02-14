package inventory.service.impl;

import inventory.dao.WarehouseInsertReleaseDao;
import inventory.service.WarehouseInsertReleaseService;
import mgtOrders.domain.MgtOrderProductsRelationship;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseInsertReleaseServiceImpl implements WarehouseInsertReleaseService {
    WarehouseInsertReleaseDao whInOutDao = new WarehouseInsertReleaseDao();

    /**
     * 현재 발주처에서 배송완료 처리가 되고 창고로 입고중인 물품을 출력하는 메소드
     * @return
     */
    @Override
    public ArrayList<MgtOrderProductsRelationship> findStatusDone() {
        // 읽어온 상품들을 저장할 ArrayList 생성
        ArrayList<MgtOrderProductsRelationship> allInsertProducts = whInOutDao.findAllInsertProducts();
        System.out.println("현재로 입고중인 상품목록 출력");
        System.out.println("=".repeat(100));
        System.out.printf("%s  %10s","상품수량","상품 ID\n");
        for (MgtOrderProductsRelationship insertProduct : allInsertProducts) {

            System.out.printf("%s   %10s\n",
                    insertProduct.getQuantity(),insertProduct.getProductsId());

        }
        System.out.println("=".repeat(100));
        // 읽어온 상품들의 정보를 담은 ArrayList 반환
        return allInsertProducts;
    }
}
// TODO: implement service

