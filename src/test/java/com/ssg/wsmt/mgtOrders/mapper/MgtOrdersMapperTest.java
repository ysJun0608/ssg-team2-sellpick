package com.ssg.wsmt.mgtOrders.mapper;

import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class MgtOrdersMapperTest {

    @Autowired
    private MgtOrdersMapper mgtOrdersMapper;

    @Test
    void searchOrders_withNullParameters_shouldReturnAllOrders() {
        // Call the searchOrders method with null parameters
        List<MgtOrders> result = mgtOrdersMapper.searchOrders(null, null, null, "1");

        System.out.println("========================================================================");
        System.out.println(result);
        System.out.println(result.get(0).getId() + " " + result.get(0).getPurchaser());
        // Verify that the result is not null
        assertNotNull(result);

        // Assuming mgtOrdersMapper returns a list of orders, perform assertions as needed
        // For example, you can check if the size of the result is as expected
        assertEquals(93, result.size());
    }

    @Test
    void searchOrders_withValidParameters_shouldReturnFilteredOrders() {
        // Create test data or mocks for input parameters
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        String purchaser = "examplePurchaser";
        int warehouseId = 1;

        // Call the searchOrders method with valid parameters
        List<MgtOrders> result = mgtOrdersMapper.searchOrders(startDate, endDate, purchaser, String.valueOf(warehouseId));

        // Verify that the result is not null
        assertNotNull(result);

        // Perform assertions on the filtered orders
        // For example, check if the returned orders match the expected criteria
        for (MgtOrders order : result) {
            assertEquals(purchaser, order.getPurchaser());
            assertEquals(warehouseId, order.getWarehouseId());
        }
    }
}
