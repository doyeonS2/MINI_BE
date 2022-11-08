//package com.kh.miniservlet.dao;
//
//import com.kh.miniservlet.common.Common;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class ResultDAO {
//    private Connection conn = null;
//    private Statement stmt = null;
//    private ResultSet rs = null;
//    private PreparedStatement pstmt = null;
//
//    public int resultCheck(String result) {
//        int resState = 200; // 200 데이터 반환 ,300 결과값 없음
//        try {
//            conn = Common.getConnection();
//            stmt = conn.createStatement(); // Statement 객체
//            String sql = "SELECT * FROM PRO_TB WHERE PRO_KORNAME LIKE '%" + result + "%';";
//            rs = stmt.executeQuery(sql); // select문이니까 executeQuery
//
//            while (rs.next()) {
//                String sqlItem = rs.getString("PRO_KORNAME");
//                String sqlPrice = rs.getString("PRICE");
//                String sqlImg = rs.getString("IMG1");
//
//                System.out.println("Item : " + sqlItem);
//                System.out.println("Price : " + sqlPrice);
//                System.out.println("Img : " + sqlImg);
//
//                if (result.equals(sqlItem)) {
//                    resState = 200;
//                } else {
//                    resState = 300;
//                }
//            }
//            Common.close(rs);
//            Common.close(stmt);
//            Common.close(conn);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return resState;
//    }
//}
