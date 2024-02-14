package inventory.service.impl;

import inventory.dao.WarehouseSectionDao;
import inventory.domain.Warehouse;
import inventory.service.WarehouseSectionService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WarehouseSectionServiceImpl implements WarehouseSectionService {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    WarehouseSectionDao warehouseSectionDao = new WarehouseSectionDao();
    @Override
    public void createWarehouseSection(Warehouse warehouse) {
        warehouseSectionDao.saveWarehouseSection(warehouse);
    }
}
