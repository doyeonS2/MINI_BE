package com.kh.miniservlet.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
// 도연, 은종, 수빈 공동 사용
@Getter
@Setter
public class MemberVO {
    // 회원
    private String id; // 회원 아이디
    private String pwd; // 회원 패스워드
    private String pwdCheck; // 패스워드 확인
    private String memName; // 회원 이름
    private String email; // 회원 이메일
    private String phone; // 회원 전화번호
    private String addr; // 주소
    private Date regDate; // 회원 가입일, 변환해줘야 함
}

