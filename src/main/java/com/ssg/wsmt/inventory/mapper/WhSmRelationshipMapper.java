package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.WhSmRelationshipVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WhSmRelationshipMapper {
    void saveWhSmRelationship(WhSmRelationshipVO whSmRelationShipVO);
}
