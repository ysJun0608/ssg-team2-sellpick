package com.ssg.wsmt.inventory.service.impl;

import com.ssg.wsmt.inventory.dao.WhSmRelationshipDao;
import com.ssg.wsmt.inventory.domain.WhSmRelationship;
import com.ssg.wsmt.inventory.service.WhSmRelationshipService;

public class WhSmRelationshipServiceImpl implements WhSmRelationshipService {
    WhSmRelationshipDao whSmRelationshipDao = new WhSmRelationshipDao();
    @Override
    public void createWhSmRelationship(WhSmRelationship whSmRelationship) {
        whSmRelationshipDao.saveWhSmRelationship(whSmRelationship);

    }
}
