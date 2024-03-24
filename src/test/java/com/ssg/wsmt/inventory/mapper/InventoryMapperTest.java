package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.WsmtApplication;
import com.ssg.wsmt.inventory.domain.InventoryVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = WsmtApplication.class)
class InventoryMapperTest {

    @Autowired
    private InventoryMapper inventoryMapper;
    @Test
    public void test() {
        List<InventoryVO> all = inventoryMapper.findAll();
        Assertions.assertThat(all).isNotNull();

    }
    @Test
    public void test2(){
        List<InventoryVO> mapperAll = inventoryMapper.findAll();

    }

}