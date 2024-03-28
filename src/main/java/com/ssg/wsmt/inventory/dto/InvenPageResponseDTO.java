package com.ssg.wsmt.inventory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class InvenPageResponseDTO<E> {

    private int page;
    private int size;
    private int total;
    private int totalPages;
    private List<E> dtoList;

    private int start;
    private int end;
    private boolean prev;
    private boolean next;

    @Builder(builderMethodName = "withAll")
    public InvenPageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        if (total <= 0) {
            return;
        }

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.totalPages = (int) Math.ceil((double) total / size);

        int visiblePages = 10;
        this.start = Math.max(1, page - visiblePages / 2);
        this.end = Math.min(start + visiblePages - 1, totalPages);

        this.prev = start > 1;
        this.next = end < totalPages;
    }
}