package com.ssg.wsmt.mgtOrders.DTO;

import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;

    // 검색 조건 필드
    private LocalDate startDate;
    private LocalDate endDate;
    private String purchaser;
    private Long warehouseId;
    private MgtOrdersStatus status;

    public Pageable getPageable(String... props) {
        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
    }

    public int getOffset() {
        return Math.max(0, (page - 1) * size);
    }

    private String link;

    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if (startDate != null) {
                builder.append("&startDate=" + startDate);
            }
            if (endDate != null) {
                builder.append("&endDate=" + endDate);
            }
            if (purchaser != null && !purchaser.isEmpty()) {
                try {
                    builder.append("&purchaser=" + URLEncoder.encode(purchaser, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    // 예외 처리
                }
            }
            if (warehouseId != null) {
                builder.append("&warehouseId=" + warehouseId);
            }

            link = builder.toString();
        }
        return link;
    }
}