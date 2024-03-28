package com.ssg.wsmt.delivery.service.impl;

import com.ssg.wsmt.delivery.service.DeliveryCmpService;
import org.springframework.stereotype.Service;
@Service
public class DeliveryCmpServiceImpl implements DeliveryCmpService {
//    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//    DeliveryCmpDao dao = new DeliveryCmpDao();

    @Override
    public Long chooseDeliveryCmp() {
//        List<DeliveryCmp> deliveryCmpList = dao.readDeliveryCmp();
//        System.out.println("=".repeat(50));
//        System.out.printf("| %s | %s \n", "택배사 번호", "택배사 이름");
//        System.out.println("=".repeat(50));
//
//        for (DeliveryCmp deliveryCmp : deliveryCmpList) {
//            System.out.printf("| %-10d| %s \n", deliveryCmp.getId(), deliveryCmp.getName());
//        }
//
//        System.out.println("=".repeat(50));
//        System.out.println("창고에서 사용할 택배사 번호를 입력해주세요");
//
//        Long chooseDcId = 0l;
//        try {
//            chooseDcId = Long.valueOf(input.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return chooseDcId;
        return null;
    }
}
