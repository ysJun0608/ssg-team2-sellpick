package inventory.service;

public interface SettlementService {
    // 정산 리스트 출력
    void settlementList();

    // 특정 날짜 정산 출력
    void settlementByDate();
}
