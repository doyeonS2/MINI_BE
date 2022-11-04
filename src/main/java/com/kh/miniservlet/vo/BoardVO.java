package com.kh.miniservlet.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BoardVO {
    private String id;
    private Integer boardNum; // 글 번호, 변환해줘야 함
    private Integer category; // 글 카테고리, 변환해줘야 함
    private String title; // 글 제목
    private String boardContent; // 글 내용
    private Date boardDate; // 글 작성일, 변환해줘야 함

}
