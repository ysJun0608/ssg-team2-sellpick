package smOrders.service.impl;

import smOrders.dao.ShoppingMallDao;
import smOrders.domain.ShoppingMall;
import smOrders.service.ShoppingMallService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.List;

public class ShoppingMallServiceImpl implements ShoppingMallService {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    ShoppingMallDao shoppingMallDao = new ShoppingMallDao();

    @Override
    public Long chooseShoppingMall() {
        List<ShoppingMall> shoppingMallList = shoppingMallDao.findAll();
        System.out.println("=".repeat(50));
        System.out.printf("| %s  | %s \n", "쇼핑몰번호", "쇼핑몰이름");
        for (ShoppingMall sm : shoppingMallList) {
            System.out.printf("| %-10d| %s \n", sm.getId(), sm.getName());

         //   System.out.println(sm);
        }

        Long smNum = null;
        try {
            System.out.println("=".repeat(50));
            System.out.println("창고와 연동할 쇼핑몰번호를 입력해주세요.");
            smNum = Long.valueOf(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return smNum;
    }
}
