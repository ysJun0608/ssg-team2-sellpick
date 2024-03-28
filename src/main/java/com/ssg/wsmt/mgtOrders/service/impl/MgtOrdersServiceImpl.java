package com.ssg.wsmt.mgtOrders.service.impl;

import com.ssg.wsmt.inventory.dto.WarehouseDTO;
import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.DTO.PageRequestDTO;
import com.ssg.wsmt.mgtOrders.DTO.PageResponseDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import com.ssg.wsmt.mgtOrders.mapper.MgtOrdersMapper;
import com.ssg.wsmt.mgtOrders.service.MgtOrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MgtOrdersServiceImpl implements MgtOrdersService {
    // TODO: Implement the service

    private final MgtOrdersMapper mgtOrdersMapper;

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    //private final MgtOrderDao mgtOrderDao = new MgtOrderDao();

    @Override
    @Transactional
    public Long createForm(MgtOrdersDTO mgtOrdersDTO) {
        // Insert the new row into the mgt_Orders table
        MgtOrders mgtOrders = MgtOrders.builder()
                .purchaser(mgtOrdersDTO.getPurchaser())
                .status(mgtOrdersDTO.getStatus())
                .warehouseId(mgtOrdersDTO.getWarehouseId())
                .build();

        mgtOrdersMapper.createOrder(mgtOrders);

        // Retrieve the generated ID value from the database
        Long generatedId = mgtOrders.getId(); // Assuming getId() retrieves the generated ID

        // Return the generated ID value
        return generatedId;
    }


    public void addItems(Long quantities, Long productId, Long id) {
        mgtOrdersMapper.addItems(quantities, productId, id);
        log.info(id);
//        for (int i = 0; i < quantities.length; i++) {
//            if (quantities[i] != null){
//                log.info("if문 안쪽으로.........." + quantities[i]);
//                mgtOrdersMapper.addItems(quantities[i], productId[i], id);
//            }
//        }
    }

    //    @Override
    public void getAllOrders() {
//
//        System.out.println("조회할 날짜를 입력하세요");
//        ArrayList<MgtOrders> searchList = new ArrayList<>();
//        try {
//            System.out.print("시작일(예시 : 20240213) : ");
//            String startDate = bufferedReader.readLine();
//            System.out.print("종료일(예시 : 20240213) : ");
//            String endDate = bufferedReader.readLine();
//
//            searchList = mgtOrdersMapper.selectAllForDate(startDate, endDate);
//
//            if (searchList.isEmpty()) {
//                System.out.println("조회된 발주목록이 없습니다.");
//                return;
//            }
//
//            printList(searchList);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public boolean confirmOrder(Long orderId) {
        Integer flag = 0;
        flag = mgtOrdersMapper.updateStatus(MgtOrdersStatus.DONE, orderId);
        log.info("orderId : " + orderId);
        return flag > 0 ;
    }

    @Override
    public boolean cancelOrder() {
        Integer flag = 0;

        try {
            System.out.print("확정 취소할 발주 ID를 입력하세요 : ");
            Long orderId = Long.parseLong(bufferedReader.readLine());

            System.out.print("확정된 주문을 취소하시겠습니까 ? y/n : ");
            String temp = bufferedReader.readLine();

            if (temp.equals("y") || temp.equals("Y")) {
                flag = mgtOrdersMapper.cancelOrder(orderId);
                if (flag > 0) {
                    System.out.println("확정된 발주를 취소했습니다.");
                } else {
                    System.out.println("발주 확정이 실패했습니다.");
                }
            } else {
                System.out.println("확정취소 메뉴를 종료합니다.");
            }

        } catch (IOException Ie) {
            Ie.printStackTrace();
        }

        return flag > 0 ? true : false;
    }

    @Override
    public void confirmList() {
        ArrayList<Long> sellectNum = new ArrayList<>();
        ArrayList<MgtOrders> mgtOrders = new ArrayList<>();
        try {
            mgtOrders = mgtOrdersMapper.searchForStatus(MgtOrdersStatus.READY);

            if (mgtOrders.isEmpty()) {
                System.out.println("확정할 발주목록이 없습니다.");
                return;
            }

            System.out.println("확정되지 않은 발주 목록입니다.");
            for (MgtOrders mgtOrder : mgtOrders) {
                print(mgtOrder);
            }

            System.out.println("확정시킬 발주목록들을 하나씩 입력해주세요.");
            System.out.println("선택이 끝나면 0를 입력해주세요");

            while (true) {
                Long input = Long.parseLong(bufferedReader.readLine());
                if (input == 0) {
                    break;
                }
                sellectNum.add(input);
            }

            for (Long num : sellectNum) {
                mgtOrdersMapper.insertList("DONE", num);
            }
            System.out.println("선택하신 발주목록을 확정처리합니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void searchNonDelivered() {
        ArrayList<MgtOrders> mgtOrders = new ArrayList<>();

        try {
            System.out.print("조회할 날짜를 입력하세요(예시 : 20240213) : ");
            String searchDate = bufferedReader.readLine();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date temp = dateFormat.parse(searchDate);
//            java.sql.Timestamp createdAt = new java.sql.Timestamp(temp.getTime());
            mgtOrders = mgtOrdersMapper.searchNoneDelived(searchDate);

            if (mgtOrders.isEmpty()) {
                System.out.println("조회된 발주목록이 없습니다.");
                return;
            }

            printList(mgtOrders);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void confirmArrival() {
        Integer flag = 0;
        ArrayList<MgtOrders> mgtOrders = new ArrayList<>();
        try {
            mgtOrders = mgtOrdersMapper.searchForStatus(MgtOrdersStatus.DONE);
            if (mgtOrders.isEmpty()) {
                System.out.println("도착확인할 발주목록이 없습니다.");
                return;
            }
            printList(mgtOrders);
            System.out.print("도착한 발주의 ID를 입력하세요 : ");
            Long orderId = Long.parseLong(bufferedReader.readLine());
            flag = mgtOrdersMapper.updateStatus(MgtOrdersStatus.DELIVERED, orderId);
            if (flag > 0) {
                System.out.println("도착확인 완료");
            } else {
                System.out.println("도착확인 실패");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long id) {
        log.info(id);
        mgtOrdersMapper.delete(id);
    }

    @Override
    public void deleteItems(Long id) {
        log.info("delete Id : " + id);
        mgtOrdersMapper.deleteItems(id);
    }

    @Override
    public List<MgtOrdersDTO> searchForStatus(MgtOrdersStatus status) {
        List<MgtOrdersDTO> mgtOrdersDTOList = mgtOrdersMapper.searchForStatus(status).stream()
                .map(o ->
                        MgtOrdersDTO.builder()
                                .id(o.getId())
                                .purchaser(o.getPurchaser())
                                .status(o.getStatus())
                                .createdAt(o.getCreatedAt())
                                .warehouseId(o.getWarehouseId())
                                .build()
                ).collect(Collectors.toList());
        return mgtOrdersDTOList;
    }

    public PageResponseDTO<MgtOrdersDTO> searchOrdersAndStatus(PageRequestDTO pageRequestDTO) {
        List<MgtOrdersDTO> mgtOrdersDTOList = mgtOrdersMapper.searchOrdersAndStatus(pageRequestDTO);

        // Get the total count of orders matching the search criteria
        int total = mgtOrdersMapper.getTotalCount(pageRequestDTO);

        return PageResponseDTO.<MgtOrdersDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(mgtOrdersDTOList)
                .total(total)
                .build();
    }

    @Override
    public List<MgtOrdersDTO> searchOrders(String startDate, String endDate, String purchaser, String warehouseId) {
        List<MgtOrdersDTO> mgtOrdersDTOList = mgtOrdersMapper.searchOrders(startDate, endDate, purchaser, warehouseId).stream()
                .map(o ->
                        MgtOrdersDTO.builder()
                                .id(o.getId())
                                .purchaser(o.getPurchaser())
                                .status(o.getStatus())
                                .createdAt(o.getCreatedAt())
                                .warehouseId(o.getWarehouseId())
                                .build()
                ).collect(Collectors.toList());

        return mgtOrdersDTOList;
    }

    @Override
    public PageResponseDTO<MgtOrdersDTO> selectAll(PageRequestDTO pageRequestDTO) {
        // WarehouseMapper를 사용하여 페이지 요청에 따른 창고 정보 및 섹션 정보를 조회하는 메서드입니다.
        List<MgtOrdersDTO> dtoList = mgtOrdersMapper.searchAll(pageRequestDTO);

        // 페이지 요청에 따른 전체 창고 수를 조회하는 메서드를 호출합니다.
        int total = mgtOrdersMapper.getTotalCount(pageRequestDTO);

        // 페이지 응답 DTO를 생성하여 반환합니다.
        return PageResponseDTO.<MgtOrdersDTO>withAll()
                // 페이지 요청 DTO를 설정합니다.
                .pageRequestDTO(pageRequestDTO)
                // 조회된 창고 및 섹션 정보 DTO 리스트를 설정합니다.
                .dtoList(dtoList)
                // 전체 창고 수를 설정합니다.
                .total(total).build();
    }

    @Override
    public MgtOrdersDTO getOne(Long id) {
        MgtOrders mgtOrders = mgtOrdersMapper.getOne(id);
        MgtOrdersDTO mgtOrdersDTO = MgtOrdersDTO.builder()
                .id(mgtOrders.getId())
                .purchaser(mgtOrders.getPurchaser())
                .status(mgtOrders.getStatus())
                .createdAt(mgtOrders.getCreatedAt())
                .warehouseId(mgtOrders.getWarehouseId())
                .build();
        return mgtOrdersDTO;
    }


    private void print(MgtOrders mgtOrder) {
        System.out.println(mgtOrder.toString());
    }


    private void printList(ArrayList<MgtOrders> mgtOrders) {
        for (MgtOrders mgtOrder : mgtOrders) {
            System.out.println(mgtOrder.toString());
        }
    }
}
