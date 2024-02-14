package inventory.service.impl;

import inventory.dao.WhSmRelationshipDao;
import inventory.domain.WhSmRelationship;
import inventory.service.WhSmRelationShipService;

public class WhSmRelationShipServiceImpl implements WhSmRelationShipService {
    WhSmRelationshipDao whSmRelationshipDao = new WhSmRelationshipDao();
    @Override
    public void createWhSmRelationShip(WhSmRelationship whSmRelationShip) {
        whSmRelationshipDao.saveWhSmRelationShip(whSmRelationShip);

    }
}
