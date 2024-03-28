package com.ssg.wsmt.inventory.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvenInOutChartDTO {

    private String year;
    private String month;
    private int insertQuantity;
    private int releaseQuantity;

}
