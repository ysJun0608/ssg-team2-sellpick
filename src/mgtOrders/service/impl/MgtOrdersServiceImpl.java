package mgtOrders.service.impl;

import mgtOrders.dao.MgtOrderDao;
import mgtOrders.domain.MgtOrder;
import mgtOrders.service.MgtOrdersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MgtOrdersServiceImpl implements MgtOrdersService {
    // TODO: Implement the service
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private final MgtOrderDao mgtOrderDao;

    public MgtOrdersServiceImpl(MgtOrderDao mgtOrderDao) {
        this.mgtOrderDao = mgtOrderDao;
    }

    @Override
    public void add() {
        Map<Integer, Integer> products = new HashMap<>();

        try {
            int productId = 0;
            int quantity = 0;
            // 발주마스터 생성
            System.out.printf("발주마스터를 생성하기 위해 매입거래처를 입력해주세요 : ");
            String purChaser = bufferedReader.readLine();

            java.util.Date today = new java.util.Date();
            java.sql.Timestamp createdAt = new java.sql.Timestamp(today.getTime());

            Long id = mgtOrderDao.createOrder(purChaser, createdAt);

            if (id <= 0L) {
                System.out.println("발주마스터 생성에 실패하였습니다. 재생성합니다.");
            }

            // 발주 할 상품 입력 받기
            while (true) {
                System.out.println("상품을 입력하겠습니다. 입력이 완료되시면 상품 Id에 exit를 입력하세요.");
                System.out.printf("발주할 상품 Id를 입력하세요. : ");
                String tempProductId = bufferedReader.readLine();
                if (tempProductId.equals("exit")) {
                    break;
                }
                System.out.printf("수량를 입력하세요. : ");
                productId = Integer.parseInt(tempProductId);
                quantity = Integer.parseInt(bufferedReader.readLine());
                products.put(productId, quantity);
            }

            boolean result = mgtOrderDao.addItem(id, products);
            if (result) {
                System.out.println("발주마스터 생성 성공");
            } else {
                System.out.println("발주마스터 생성 실패");
            }
        } catch (IOException ioE) {
            ioE.printStackTrace();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }



    @Override
    public void getAllOrders() {

        System.out.println("조회할 날짜를 입력하세요");
        ArrayList<MgtOrder> searchList = new ArrayList<>();
        try {
            System.out.print("시작일(예시 : 20240213) : ");
            String startDate = bufferedReader.readLine();
            System.out.print("종료일(예시 : 20240213) : ");
            String endDate = bufferedReader.readLine();

            searchList = mgtOrderDao.selectAll(startDate, endDate);
            printList(searchList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException Se) {
            Se.printStackTrace();
        }
    }

    @Override
    public boolean confirmOrder() {
        boolean flag = false;
        try {
            System.out.print("확정할 발주 ID를 입력하세요 : ");
            Long orderId = Long.parseLong(bufferedReader.readLine());

            System.out.print("주문을 확정하시겠습니까 ? y/n : ");
            String temp = bufferedReader.readLine();

            if (temp.equals("y") || temp.equals("Y")) {
                flag = mgtOrderDao.confirmOrder(orderId);
                if (flag) {
                    System.out.println("발주가 확정되었습니다.");
                } else {
                    System.out.println("등록된 발주가 없습니다.");
                }
            } else {
                System.out.println("발주 확정 메뉴를 종료합니다.");
            }
        } catch (IOException Ie) {
            Ie.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    @Override
    public boolean cancelOrder() {
        boolean flag = false;

        try {
            System.out.print("확정 취소할 발주 ID를 입력하세요 : ");
            Long orderId = Long.parseLong(bufferedReader.readLine());

            System.out.print("확정된 주문을 취소하시겠습니까 ? y/n : ");
            String temp = bufferedReader.readLine();

            if (temp.equals("y") || temp.equals("Y")) {
                flag = mgtOrderDao.cancelOrder(orderId);
                if (flag) {
                    System.out.println("확정된 발주를 취소했습니다.");
                } else {
                    System.out.println("발주 확정이 실패했습니다.");
                }
            } else {
                System.out.println("확정취소 메뉴를 종료합니다.");
            }

        } catch (IOException Ie) {
            Ie.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return flag;
    }

    @Override
    public void confirmList() {
        ArrayList<Long> sellectNum = new ArrayList<>();
        ArrayList<MgtOrder> mgtOrders = new ArrayList<>();
        try {
            mgtOrders = mgtOrderDao.selectOrderList("READY");

            System.out.println("확정되지 않은 발주 목록입니다.");

            for (MgtOrder mgtOrder : mgtOrders) {
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
            mgtOrderDao.insertList(sellectNum, "DONE");
            System.out.println("선택하신 발주목록을 확정처리합니다.");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void searchNonDelivered() {
        ArrayList<MgtOrder> mgtOrders = new ArrayList<>();

        try {
            System.out.print("조회할 날짜를 입력하세요(예시 : 20240213) : ");
            String searchDate = bufferedReader.readLine();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date temp = dateFormat.parse(searchDate);
//            java.sql.Timestamp createdAt = new java.sql.Timestamp(temp.getTime());
            mgtOrders = mgtOrderDao.searchForDate(searchDate);

            printList(mgtOrders);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException Se) {
            Se.printStackTrace();
        }
//        catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void delete() {
        boolean flag = false;
        System.out.print("삭제할 발주 ID를 입력하세요 : ");
        try {
            Long orderId = Long.parseLong(bufferedReader.readLine());
            flag = mgtOrderDao.delete(orderId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (flag) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }
        }
    }


    private void print(MgtOrder mgtOrder) {
        System.out.println(mgtOrder.toString());
    }


    private void printList(ArrayList<MgtOrder> mgtOrders) {
        for (MgtOrder mgtOrder : mgtOrders) {
            System.out.println(mgtOrder.toString());
        }
    }
}
