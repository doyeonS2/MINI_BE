package com.kh.miniservlet.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ItemVO {
    private String proCode; // 상품번호
    private String brand; // 상품브랜드
    private String proName; // 상품이름
    private String proKorName; // 상품 한글이름
    private Date launDate; // 상품 발매일, 변환해줘야 함
    private Integer price; // 상품 가격, 변환해줘야 함
    private Integer size; // 상품 사이즈, 변환해줘야 함
    private String img1Path; // 이미지경로(img1 : 메인이미지)
    private String img2Path; // 이미지경로(img2 : 서브이미지1)
    private String img3Path; // 이미지경로(img3 : 서브이미지2)
}
