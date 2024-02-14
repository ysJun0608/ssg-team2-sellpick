package inventory.service.impl;

import inventory.dao.WhSmRelationshipDao;
import inventory.domain.WhSmRelationship;
import inventory.service.WhSmRelationshipService;

public class WhSmRelationshipServiceImpl implements WhSmRelationshipService {
    WhSmRelationshipDao whSmRelationshipDao = new WhSmRelationshipDao();
    @Override
    public void createWhSmRelationship(WhSmRelationship whSmRelationship) {
        whSmRelationshipDao.saveWhSmRelationship(whSmRelationship);

    }
}
