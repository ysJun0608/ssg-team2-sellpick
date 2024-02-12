package inventory.domain;

public class WhInOutSettlementRelationship {
    // 입출고 정산 관계 id
    private Long id;
    // 입출고 id (FK)
    private Long whInsertReleaseId;
    // 마감 id (FK)
    private Long settlementId;

    // GETTER, SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWhInsertReleaseId() {
        return whInsertReleaseId;
    }

    public void setWhInsertReleaseId(Long whInsertReleaseId) {
        this.whInsertReleaseId = whInsertReleaseId;
    }

    public Long getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(Long settlementId) {
        this.settlementId = settlementId;
    }
}