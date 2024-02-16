package inventory.service.impl;

import inventory.dao.SettlementDao;
import inventory.domain.Settlement;
import inventory.service.SettlementService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class SettlementServiceImpl implements SettlementService {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private SettlementDao settlementDao = new SettlementDao();
    @Override
    public void settlementList() {
        System.out.println("정산 리스트 출력");
        Optional<List<Settlement>> settlementList = settlementDao.settlementList();

        if (settlementList.isEmpty()) {
            System.out.println("정산 리스트가 존재하지 않습니다.");
            return;
        }

        for (Settlement settlement : settlementList.get()) {
            System.out.println(settlement);
        }
    }

    @Override
    public void settlementByDate() {
        System.out.println("특정 날짜 정산 출력");
        System.out.print("찾고 싶은 정산 날짜를 입력하세요.(날짜 입력 방식 2024-02-02) : ");
        String date = null;
        try {
            date = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Optional<Settlement> settlement = settlementDao.settlementByDate(date);

        if (settlement == null) {
            System.out.println("해당 날짜의 정산이 존재하지 않습니다.");
            return;
        }
        System.out.println(settlement.get());
    }
}
