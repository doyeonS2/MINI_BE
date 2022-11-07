//package com.kh.miniservlet.dao;
//
//import com.kh.miniservlet.common.Common;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//// 로그인한 ID의 이메일 정보 불러오기
//public class MemDAO {
//    private Connection conn = null;
//    private Statement stmt = null;
//    private ResultSet rs = null;
//    private PreparedStatement pstmt = null;
//
//    public String memInfo(String id) {
//        String email = "NONE";
//        try {
//            conn = Common.getConnection();
//            stmt = conn.createStatement();
//            String sql = "SELECT MEM_TB WHERE ID = " + "'" +id+ "'";
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()) {
//                email = rs.getString("EMAIL");
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        Common.close(rs);
//        Common.close(stmt);
//        Common.close(conn);
//        return email;
//    }
//}
