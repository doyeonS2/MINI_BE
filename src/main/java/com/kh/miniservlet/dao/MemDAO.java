package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.BoardVO;
import com.kh.miniservlet.vo.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// 로그인한 ID의 이메일 정보 불러오기
public class MemDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    public List<MemberVO> memInfo(String id) {
        String email = "NONE";
        List<MemberVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEM_TB WHERE ID = " + "'" +id+ "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                email = rs.getString("EMAIL");
                String name = rs.getString("NAME");
                MemberVO vo = new MemberVO();
                vo.setEmail(email);
                vo.setMemName(name);
                list.add(vo);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return list;
    }
}
