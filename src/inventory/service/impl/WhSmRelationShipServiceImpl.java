package inventory.service.impl;

import inventory.dao.WhSmRelationshipDao;
import inventory.domain.WhSmRelationShip;
import inventory.service.WarehouseService;
import inventory.service.WhSmRelationShipService;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class WhSmRelationShipServiceImpl implements WhSmRelationShipService {
    WhSmRelationshipDao whSmRelationshipDao = new WhSmRelationshipDao();
    @Override
    public void createWhSmRelationShip(WhSmRelationShip whSmRelationShip) {
        whSmRelationshipDao.saveWhSmRelationShip(whSmRelationShip);

    }
}
