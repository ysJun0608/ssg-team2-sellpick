package com.ssg.wsmt.inventory.domain;

import lombok.Data;

@Data
public class WhInOutSettlementRelationshipVO {
    // 입출고 정산 관계 id
    private Long id;
    // 입출고 id (FK)
    private Long whInsertReleaseId;
    // 마감 id (FK)
    private Long settlementId;
}
