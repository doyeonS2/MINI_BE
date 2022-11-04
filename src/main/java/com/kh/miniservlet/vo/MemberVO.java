package com.kh.miniservlet.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
    private String id; // 회원 아이디
    private String pwd; // 회원 패스워드
    private String pwdCheck; // 패스워드 확인
    private String memName; // 회원 이름
    private String email; // 회원 이메일
}
