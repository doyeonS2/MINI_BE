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
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("회원정보 수정 중 예외 발생");
            e.printStackTrace();
        }
    }
}
