package com.ssg.wsmt.inventory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter // 모든 필드에 대해 getter 메서드를 생성하는 롬복 어노테이션입니다.
@ToString // 쉬운 디버깅을 위해 toString() 메서드를 생성하는 롬복 어노테이션입니다.
public class PageResponseDTO<E> {

    // 페이지 정보를 나타내는 필드들
    private int page; // 현재 페이지 번호
    private int size; // 한 페이지당 아이템 개수
    private int total; // 전체 아이템 개수

    // 페이지네이션 정보를 나타내는 필드들
    private int start; // 시작 페이지 번호
    private int end; // 끝 페이지 번호
    private boolean prev; // 이전 페이지의 존재 여부
    private boolean next; // 다음 페이지의 존재 여부

    private List<E> dtoList; // 현재 페이지의 DTO 리스트

    // PageResponseDTO 객체를 생성하기 위한 빌더 패턴 메서드
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        // 전체 아이템 개수가 양수가 아니면 추가 처리 없이 리턴합니다.
        if(total <= 0){
            return;
        }

        // 페이지 요청 DTO 객체에서 값을 할당합니다.
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total; // 전체 아이템 개수를 할당합니다.
        this.dtoList = dtoList; // 현재 페이지의 DTO 리스트를 할당합니다.

        // 페이지네이션을 위한 시작과 끝 페이지 번호를 계산합니다.
        this.end =   (int)(Math.ceil(this.page / 10.0 )) *  10; // 끝 페이지 번호를 계산합니다.
        this.start = this.end - 9; // 시작 페이지 번호를 계산합니다.

        // 전체 페이지 개수를 계산합니다.
        int last =  (int)(Math.ceil((total/(double)size)));

        // 끝 페이지 번호가 마지막 페이지를 넘지 않도록 조정합니다.
        this.end =  end > last ? last: end;

        // 이전 페이지의 존재 여부를 판단합니다.
        this.prev = this.start > 1;

        // 다음 페이지의 존재 여부를 판단합니다.
        this.next =  total > this.end * this.size;

    }
}
