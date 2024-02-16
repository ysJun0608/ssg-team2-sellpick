package inventory.service.impl;

import inventory.dao.WarehouseInsertReleaseDao;
import inventory.enums.WhInOutType;
import inventory.service.WarehouseInsertReleaseService;
import mgtOrders.domain.WarehouseInsertRelease;

import java.util.ArrayList;
import java.util.Scanner;

public class WarehouseInsertReleaseServiceImpl implements WarehouseInsertReleaseService {
    WarehouseInsertReleaseDao whInOutDao = new WarehouseInsertReleaseDao();
    Scanner sc = new Scanner(System.in);

    /**
     * 현재 발주처에서 배송완료 처리가 되고 창고로 입고중인 물품을 출력하는 메소드
     * @return
     */
    @Override
    public ArrayList<WarehouseInsertRelease> findStatusDone() {
        // 읽어온 상품들을 저장할 ArrayList 생성
        ArrayList<WarehouseInsertRelease> allInsertProducts = whInOutDao.findAllInsertProducts();
        if (allInsertProducts.isEmpty()) {
            System.out.println("현재 입고중인 상품이 없습니다.");
            return null;
        }
        System.out.println("현재로 입고중인 상품목록 출력");
        System.out.println("=".repeat(100));
        System.out.printf("%s  %10s","상품수량","상품 ID\n");
        for (WarehouseInsertRelease insertProduct : allInsertProducts) {

            System.out.printf("%s   %10s\n",
                    insertProduct.getQuantity(),insertProduct.getProductsId());

        }
        System.out.println("=".repeat(100));
        // 읽어온 상품들의 정보를 담은 ArrayList 반환
        return allInsertProducts;
    }

    @Override
    public void updateReleaseStatus(WhInOutType whInOutType) {
        System.out.println("출고 준비중인 리스트 출력");
        System.out.println("=".repeat(100));
        ArrayList<WarehouseInsertRelease> WhInOutList = whInOutDao.findAllInsertProducts();

        for (WarehouseInsertRelease whInOut : WhInOutList) {
            System.out.println(whInOut);
        }
        System.out.println("=".repeat(100));

        System.out.println("출고를 할 id를 입력해주세요 : ");
        Long id = Long.parseLong(sc.nextLine());
        whInOutDao.updateWhInOutStatus(id, whInOutType);
    }
}

