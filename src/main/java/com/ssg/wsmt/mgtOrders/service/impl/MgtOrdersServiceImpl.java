package com.ssg.wsmt.mgtOrders.service.impl;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import com.ssg.wsmt.mgtOrders.mapper.MgtOrdersMapper;
import com.ssg.wsmt.mgtOrders.service.MgtOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MgtOrdersServiceImpl implements MgtOrdersService {
    // TODO: Implement the service

    private final MgtOrdersMapper mgtOrdersMapper;

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    //private final MgtOrderDao mgtOrderDao = new MgtOrderDao();

    @Override
    public void add(MgtOrdersDTO mgtOrdersDTO) {
        Map<Integer, Integer> products = new HashMap<>();

        try {
//            int productId = 0;
//            int quantity = 0;
//            // 발주마스터 생성
//            System.out.printf("발주마스터를 생성하기 위해 매입거래처를 입력해주세요 : ");
//            String purChaser = bufferedReader.readLine();
//            System.out.println("창고 ID를 입력해주세요");
//            Long whId = Long.parseLong(bufferedReader.readLine());
//
//            java.util.Date today = new java.util.Date();
//            java.sql.Timestamp createdAt = new java.sql.Timestamp(today.getTime());
//
            MgtOrders mgtOrders = MgtOrders.builder()
                    .id(mgtOrdersDTO.getId())
                    .purchaser(mgtOrdersDTO.getPurchaser())
                    .status(mgtOrdersDTO.getStatus())
                    .createdAt(mgtOrdersDTO.getCreatedAt())
                    .warehouseId(mgtOrdersDTO.getWarehouseId())
                    .build();
            mgtOrdersMapper.createOrder(mgtOrders);

//            if (result <= 0L) {
//                System.out.println("발주양식 생성에 실패하였습니다. 재생성합니다.");
//            }

            // 발주 할 상품 입력 받기
//            while (true) {
//                System.out.println("상품을 입력하겠습니다. 입력이 완료되시면 상품 Id에 exit를 입력하세요.");
//                System.out.printf("발주할 상품 Id를 입력하세요. : ");
//                String tempProductId = bufferedReader.readLine();
//                if (tempProductId.equals("exit")) {
//                    break;
//                }
//                System.out.printf("수량를 입력하세요. : ");
//                productId = Integer.parseInt(tempProductId);
//                quantity = Integer.parseInt(bufferedReader.readLine());
//                products.put(productId, quantity);
//            }
//
//            for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
//                boolean result = mgtOrdersMapper.addItem(entry.getValue(), entry.getKey(), id);
//                if (result) {
//                    System.out.println("발주 리스트 생성 성공");
//                } else {
//                    System.out.println("발주 리스트 생성 실패");
//                }
//            }
//        } catch (IOException ioE) {
//            ioE.printStackTrace();
//        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public void getAllOrders () {

            System.out.println("조회할 날짜를 입력하세요");
            ArrayList<MgtOrders> searchList = new ArrayList<>();
            try {
                System.out.print("시작일(예시 : 20240213) : ");
                String startDate = bufferedReader.readLine();
                System.out.print("종료일(예시 : 20240213) : ");
                String endDate = bufferedReader.readLine();

                searchList = mgtOrdersMapper.selectAllForDate(startDate, endDate);

                if (searchList.isEmpty()) {
                    System.out.println("조회된 발주목록이 없습니다.");
                    return;
                }

                printList(searchList);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean confirmOrder () {
            Integer flag = 0;
            try {
                System.out.print("확정할 발주 ID를 입력하세요 : ");
                Long orderId = Long.parseLong(bufferedReader.readLine());

                System.out.print("주문을 확정하시겠습니까 ? y/n : ");
                String temp = bufferedReader.readLine();

                if (temp.equals("y") || temp.equals("Y")) {
                    flag = mgtOrdersMapper.updateStatus(MgtOrdersStatus.DONE, orderId);
                    if (flag > 0) {
                        System.out.println("발주가 확정되었습니다.");
                    } else {
                        System.out.println("등록된 발주가 없습니다.");
                    }
                } else {
                    System.out.println("발주 확정 메뉴를 종료합니다.");
                }
            } catch (IOException Ie) {
                Ie.printStackTrace();
            }
            return flag > 0 ? true : false;
        }

        @Override
        public boolean cancelOrder () {
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
        public void confirmList () {
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
        public void searchNonDelivered () {
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
        public void confirmArrival () {
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
        public void delete () {
            Integer flag = 0;
            System.out.print("삭제할 발주 ID를 입력하세요 : ");
            try {
                Long orderId = Long.parseLong(bufferedReader.readLine());
                flag = mgtOrdersMapper.delete(orderId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (flag > 0) {
                    System.out.println("삭제 성공");
                } else {
                    System.out.println("삭제 실패");
                }
            }
        }

        @Override
        public List<MgtOrdersDTO> selectAll () {
            List<MgtOrdersDTO> mgtOrdersDTOList = mgtOrdersMapper.selectAll().stream()
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
        public MgtOrdersDTO getOne (Long id){
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


        private void print (MgtOrders mgtOrder){
            System.out.println(mgtOrder.toString());
        }


        private void printList (ArrayList < MgtOrders > mgtOrders) {
            for (MgtOrders mgtOrder : mgtOrders) {
                System.out.println(mgtOrder.toString());
            }
        }
    }
