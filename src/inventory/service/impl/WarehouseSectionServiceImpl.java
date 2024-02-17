package inventory.service.impl;

import inventory.dao.WarehouseDao;
import inventory.dao.WarehouseSectionDao;
import inventory.domain.Warehouse;
import inventory.domain.WarehouseSection;
import inventory.enums.WhSectionType;
import inventory.service.WarehouseSectionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WarehouseSectionServiceImpl implements WarehouseSectionService {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    WarehouseSectionDao warehouseSectionDao = new WarehouseSectionDao();
    WarehouseDao warehouseDao = new WarehouseDao();

    @Override
    public void createWarehouseSection(Warehouse warehouse) {
        WarehouseSection section = new WarehouseSection();
        section.setWarehouseId(warehouse.getId());
        try {
            Long countSection = null;
            while (true) {
                System.out.println("창고 구역을 몇개 생성할지 정해주세요(최대 4개)");
                System.out.println("=".repeat(50));
                countSection = Long.parseLong(input.readLine());
                if (countSection > 4) {

                    System.out.println("최대 구역은 4개입니다. 다시 입력해주세요");
                    System.out.println("=".repeat(50));
                    continue;
                }

                System.out.println(countSection + "개의 구역을 생성하겠습니다.");
                System.out.println("=".repeat(50));
                if (countSection <= 4) break;
            }

            System.out.println("각 구역의 세부타입메뉴를 정하시오");

            System.out.println("1. 냉장 | 2. 냉동 | 3. 건조 | 4. 가공");
            System.out.println("=".repeat(50));


            for (int i = 0; i < countSection; i++) {
                System.out.println("구역이름은 타입 뒤에 창고ID로 붙습니다.");
                System.out.println("=".repeat(50));
                String num = input.readLine();
                switch (num) {
                    case "1" -> {

                        section.setName("냉장" + " " + warehouse.getId());
                        section.setType(WhSectionType.REFRIGERATED);
                        boolean ack = warehouseSectionDao.saveWarehouseSection(section);
                        if (ack == true) System.out.println("냉장구역이 추가되었습니다.");
                        System.out.println("=".repeat(50));
                    }
                    case "2" -> {

                        section.setName("냉동" + " " + warehouse.getId());
                        section.setType(WhSectionType.FROZEN);
                        boolean ack = warehouseSectionDao.saveWarehouseSection(section);
                        if (ack == true) System.out.println("냉동구역이 추가되었습니다.");
                        System.out.println("=".repeat(50));}
                    case "3" -> {
                        section.setName("건조" + " " + warehouse.getId());
                        section.setType(WhSectionType.DRY);
                        boolean ack = warehouseSectionDao.saveWarehouseSection(section);
                        if (ack == true) System.out.println("건조구역이 추가되었습니다.");
                        System.out.println("=".repeat(50));}
                    case "4" -> {
                        section.setName("가공" + " " + warehouse.getId());
                        section.setType(WhSectionType.PROCESSED);
                        boolean ack = warehouseSectionDao.saveWarehouseSection(section);
                        if (ack == true) System.out.println("가공구역이 추가되었습니다.");
                        System.out.println("=".repeat(50));}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void readWarehouseSection() {
        ArrayList<WarehouseSection> warehouseSection = new ArrayList<>();
        List<Warehouse> warehouses = warehouseDao.readWarehouse();
        if (warehouses.isEmpty()) {
            System.out.println("보유하고계신 창고가 없습니다. 창고를 먼저 생성해주세요");
            return;
        }
        Long chooScId = null;
        System.out.println("=".repeat(50));
        System.out.printf("%-1s | %-4s | %-4s | %-4s\n", "창고번호", "택배사번호", "창고타입", "지역");
        System.out.println("=".repeat(50));
        for (Warehouse w : warehouses) {
            System.out.printf("%-7d | %-7d | %-6s | %-4s\n", w.getId(), w.getDelivery_id(), w.getType(), w.getLocation());
        }
        try {
            System.out.println("현재 보유중이신 창고구역을 조회해드리겠습니다.");
            System.out.println("보유하고계신 창고번호를 입력해주세요");
            chooScId = Long.parseLong(input.readLine());
            warehouseSection = warehouseSectionDao.selectWarehouseSection(chooScId);
            System.out.println("=".repeat(50));
            System.out.printf("%s | %-10s \n", "구역이름", "구역타입");
            for (WarehouseSection section : warehouseSection) {

                System.out.printf("%s | %-10s\n", section.getName(), section.getType());

            }
            System.out.println("=".repeat(50));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
