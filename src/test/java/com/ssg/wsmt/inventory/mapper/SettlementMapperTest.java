package com.ssg.wsmt.inventory.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SettlementMapperTest {

    @Autowired
    private SettlementMapper settlementMapper;

    @Test
    public void test() throws Exception{
        System.out.println(settlementMapper.selectAll());
    }

}
