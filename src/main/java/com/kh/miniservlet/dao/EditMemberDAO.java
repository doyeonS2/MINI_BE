package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.BoardVO;
import com.kh.miniservlet.vo.MemberVO;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EditMemberDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    // 회원 정보 수정을위한 정보 불러오기
    public List<MemberVO> MemInfo(String id) {
        int resState = 300; // 200은 로그인, 300은 Id없음, 400은 pwd 오류 => 이걸 정리한게 API문서

        List<MemberVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체
            String sql = "SELECT * FROM MEM_TB WHERE ID = " + "'" + id + "'";
            rs = stmt.executeQuery(sql); // select문이니까 executeQuery

            // id없으면 진입불가
            while(rs.next()) { // 읽은 데이터가 있으면 true
                String sqlId = rs.getString("ID"); // 쿼리문 수행 결과에서 ID값을 가져 옴
                String sqlPwd = rs.getString("PASSWORD");
                String sqlName = rs.getString("NAME");
                String sqlEmail = rs.getString("EMAIL");
                String sqlPhone = rs.getString("PHONE");
                String sqlAddr = rs.getString("ADDR");
                String sqlDate = rs.getString("REG_DATE");


                MemberVO vo = new MemberVO();


                vo.setId(sqlId);
                vo.setPwd(sqlPwd);
                vo.setMemName(sqlName);
                vo.setEmail(sqlEmail);
                vo.setPhone(sqlPhone);
                vo.setAddr(sqlAddr);
                list.add(vo);

                System.out.println("ID : " + sqlId);
                System.out.println("PASSWORD : " + sqlPwd);
                System.out.println("NAME : " + sqlName);

            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //회원 정보 수정
    public boolean editInfo(String id, String pwd, String name, String email, String phone, String addr) {
        int result = 0;
        try {

            conn = Common.getConnection();
            String sql = "UPDATE MEM_TB SET PASSWORD = ?, NAME = ?, EMAIL = ?, PHONE = ?, ADDR = ? WHERE ID = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pwd);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, addr);
            pstmt.setString(6, id);
            result = pstmt.executeUpdate(); // 페이지에 뿌릴때는 stmt.executeQuery(결과값이 있고 단순히 호출할 때)


        } catch (Exception e) {
            System.out.println("회원정보 수정 중 예외 발생");
            e.printStackTrace(); //에러가 맨 위로 올라가게 해줌(에러찾기 쉽게해주는)
        }
        Common.close(pstmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;

    }
}
