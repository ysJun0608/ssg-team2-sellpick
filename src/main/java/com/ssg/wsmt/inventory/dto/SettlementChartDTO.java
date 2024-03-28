package com.ssg.wsmt.inventory.dto;

import lombok.Data;

@Data
public class SettlementChartDTO {
    private String year;
    private String month;
    private int inQuantity;
    private int outQuantity;
}
