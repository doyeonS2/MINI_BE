package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EditMemberDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    // 회원 정보 수정을위한 정보 불러오기
    // 로그인이 되어있는 상태면 회원정보 수정가능하도록
    // 로그인이 안되어있는 상태면 로그인하도록
    // 여기에 화면에 뿌려주기 위한 Select문이 필여함(복붙해서 셀렉트문으로 바꿔_)
    public void MemInfo(MemberVO merberVO) {
        String pwd = merberVO.getPwd();
        String name = merberVO.getMemName();
        String email = merberVO.getEmail();
        String phone = merberVO.getPhone();
        String addr = merberVO.getAddr();

        try {

            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체
            String sql = "SELECT * MEM_TB WHERE ID = " + "'" + merberVO + "'";
            rs = stmt.executeQuery(sql);

            stmt.executeQuery(sql); // 페이지에 뿌릴때는 stmt.executeQuery(결과값이 있고 단순히 호출할 때)

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("회원정보 수정 중 예외 발생");
            e.printStackTrace(); //에러가 맨 위로 올라가게 해줌(에러찾기 쉽게해주는)
        }
    }
    //회원 정보 수정
    public void editInfo(MemberVO merberVO) {
        String pwd = merberVO.getPwd();
        String name = merberVO.getMemName();
        String email = merberVO.getEmail();
        String phone = merberVO.getPhone();
        String addr = merberVO.getAddr();

        try {

            conn = Common.getConnection();
            String sql = "UPDATE MEM_TB SET PWD = ?, NAME = ?, EMAIL = ?, PHONE = ?, ADDR = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pwd);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, addr);
            pstmt.executeUpdate(); // 페이지에 뿌릴때는 stmt.executeQuery(결과값이 있고 단순히 호출할 때)

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("회원정보 수정 중 예외 발생");
            e.printStackTrace(); //에러가 맨 위로 올라가게 해줌(에러찾기 쉽게해주는)
        }
    }
}
