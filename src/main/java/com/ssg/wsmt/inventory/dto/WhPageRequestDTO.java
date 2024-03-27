package com.ssg.wsmt.inventory.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhPageRequestDTO {

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    private String link;

    private String type; // WhInOutType(입고 출고 둘다)

    private String keyword;

    private LocalDate from;

    private LocalDate to;

    @Builder.Default
    @Positive
    private Long warehouseId = 0L;

    public int getSkip(){

        return (page -1) * 10;
    }

    public String getLink() {
        if(link == null){
            StringBuilder builder = new StringBuilder();

            StringBuilder append = builder.append("page=" + this.page);

            builder.append("&size=" + this.size);
            link = builder.toString();
        }
        return link;
    }

}
