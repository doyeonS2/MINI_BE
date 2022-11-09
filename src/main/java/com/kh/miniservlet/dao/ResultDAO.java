package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.ItemVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    // 검색 기능 1번째 방법
//    public List<> researchCheck(String result) {
//        int resState = 200; // 200 데이터 반환 ,300 결과값 없음
//        try {
//            conn = Common.getConnection();
//            stmt = conn.createStatement(); // Statement 객체
//            String sql = "SELECT * FROM PRO_TB WHERE PRO_KORNAME LIKE '%" + result + "%'";
//            rs = stmt.executeQuery(sql); // select문이니까 executeQuery
//
//            while (rs.next()) {
//                String sqlProcode = rs.getString("PRO_CODE");
//                String sqlItem = rs.getString("PRO_NAME");
//                String sqlKoItem = rs.getString("PRO_KORNAME");
//                String sqlPrice = rs.getString("PRICE");
//                String sqlImg = rs.getString("IMG1");
//
//                System.out.println("Procode : " + sqlProcode);
//                System.out.println("Item : " + sqlItem);
//                System.out.println("Item : " + sqlKoItem);
//                System.out.println("Price : " + sqlPrice);
//                System.out.println("Img : " + sqlImg);
//
//                ItemVO vo = new ItemVO();
//
//                // 포스트맨으로 확인 => 300 출력됨..!
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


    // 검색 기능 2번째 방법
    public List<ItemVO> researchCheck(String result) {
        List<ItemVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM PRO_TB WHERE PRO_KORNAME LIKE '%" + result + "%'" + "OR PRO_NAME LIKE '%" + result + "%'" + "OR BRAND LIKE '%" + result + "%'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String proCode = rs.getString("PRO_CODE");
                String brand = rs.getString("BRAND");
                String img1Path = rs.getString("IMG1");
                String proKorName = rs.getString("PRO_KORNAME");
                String proName = rs.getString("PRO_NAME");
                Integer price = rs.getInt("PRICE");
                Date launDate = rs.getDate("LAUN_DATE");

                ItemVO vo = new ItemVO();

                vo.setProCode(proCode);
                vo.setImg1Path(img1Path);
                vo.setProKorName(proKorName);
                vo.setBrand(brand);
                vo.setProName(proName);
                vo.setPrice(price);
                vo.setLaunDate(launDate);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
