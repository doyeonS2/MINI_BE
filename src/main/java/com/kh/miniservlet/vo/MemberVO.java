package com.kh.miniservlet.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MemberVO {
    // 회원
    private String id; // 회원 아이디
    private String pwd; // 회원 패스워드
    private String pwdCheck; // 패스워드 확인
    private String memName; // 회원 이름
    private String email; // 회원 이메일
    private String emailName; // @메일명
    private String phone; // 회원 전화번호 010
    private String phone2; // 회원 전화번호 중간 번호
    private String phone3; // 회원 전화번호 마지막 번호
    private String phoneNum; // 인증번호 6자리
    private String addrNum; // 우편번호
    private String addr1; // 주소
    private String addr2; // 상세 주소
    private Date regDate; // 회원 가입일, 변환해줘야 함
}
