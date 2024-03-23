package com.ssg.wsmt.inventory.dto; // 패키지 선언: com.ssg.wsmt.inventory.dto 패키지에 속하는 클래스 정의

import lombok.AllArgsConstructor; // lombok 라이브러리의 @AllArgsConstructor 애노테이션을 import: 모든 필드를 인자로 받는 생성자를 자동으로 생성
import lombok.Builder; // lombok 라이브러리의 @Builder 애노테이션을 import: 빌더 패턴을 자동으로 생성
import lombok.Data; // lombok 라이브러리의 @Data 애노테이션을 import: 게터, 세터, toString, equals, hashCode 메서드를 자동으로 생성
import lombok.NoArgsConstructor; // lombok 라이브러리의 @NoArgsConstructor 애노테이션을 import: 매개변수가 없는 기본 생성자를 자동으로 생성
import org.springframework.data.domain.PageRequest; // 스프링 데이터의 PageRequest 클래스 import
import org.springframework.data.domain.Pageable; // 스프링 데이터의 Pageable 인터페이스 import
import org.springframework.data.domain.Sort; // 스프링 데이터의 Sort 클래스 import

import java.io.UnsupportedEncodingException; // java.io 패키지의 UnsupportedEncodingException 클래스 import
import java.net.URLEncoder; // java.net 패키지의 URLEncoder 클래스 import

@Builder // 빌더 패턴을 사용할 수 있도록 지정된 클래스임을 표시
@Data // 게터, 세터, toString, equals, hashCode 메서드를 자동으로 생성하기 위한 lombok 애노테이션
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자를 자동으로 생성하기 위한 lombok 애노테이션
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 자동으로 생성하기 위한 lombok 애노테이션
public class PageRequestDTO { // PageRequestDTO 클래스 선언

    @Builder.Default // 빌더 패턴의 기본 값을 설정할 때 사용되는 lombok 애노테이션
    private int page = 0; // page 필드 선언 및 기본 값 설정

    @Builder.Default // 빌더 패턴의 기본 값을 설정할 때 사용되는 lombok 애노테이션
    private int size = 10; // size 필드 선언 및 기본 값 설정

    private String type; // type 필드 선언: 검색의 종류를 나타내는 문자열

    private String keyword; // keyword 필드 선언: 검색어를 나타내는 문자열

    public String[] getTypes(){ // getTypes 메서드 선언: 검색 종류를 배열로 반환하는 메서드
        if(type == null || type.isEmpty()){ // 만약 type이 null이거나 비어있으면
            return null; // null 반환
        }
        return type.split(""); // type 문자열을 한 글자씩 잘라서 배열로 반환
    }

    public Pageable getPageable(String...props) { // getPageable 메서드 선언: Pageable 인터페이스를 구현하는 PageRequest 객체를 반환하는 메서드
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending()); // page, size를 기준으로 정렬된 PageRequest 객체 반환
    }

    private String link; // link 필드 선언: 페이지 링크를 나타내는 문자열

    public String getLink() { // getLink 메서드 선언: 페이지 링크를 반환하는 메서드
        if(link == null){ // 만약 link가 null이면
            StringBuilder builder = new StringBuilder(); // StringBuilder 객체 생성

            builder.append("page=" + this.page); // "page=" + this.page를 builder에 추가

            builder.append("&size=" + this.size); // "&size=" + this.size를 builder에 추가

            if(type != null && type.length() > 0){ // 만약 type이 null이 아니고 길이가 0보다 크면
                builder.append("&type=" + type); // "&type=" + type을 builder에 추가
            }

            if(keyword != null){ // 만약 keyword가 null이 아니면
                try { // URLEncoder.encode 메서드 호출 시 예외 처리
                    builder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8")); // "&keyword=" + URLEncoder.encode(keyword,"UTF-8")를 builder에 추가
                } catch (UnsupportedEncodingException e) { // 예외 발생 시
                }
            }
            link = builder.toString(); // link에 builder에 저장된 문자열을 할당
        }

        return link; // link 반환
    }
}
