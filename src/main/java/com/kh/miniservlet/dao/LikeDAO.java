package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.BoardVO;
import com.kh.miniservlet.vo.LikeVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    public List<LikeVO> LikeInfo(String getId) {
        List<LikeVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체
//            String sql = "SELECT * FROM LIKE_TB WHERE ID = " + "'" + getId + "'";
            String sql = "SELECT L.PRO_CODE, L.ID, P.BRAND, " +
                    "P.PRO_NAME, P.PRO_KORNAME, P.LAUN_DATE, P.PRICE, P.IMG1 " +
                    "FROM LIKE_TB L JOIN PRO_TB P " +
                    "ON L.PRO_CODE = P.PRO_CODE " +
                    "WHERE L.ID = " + "'" + getId + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {

                String id = rs.getString("ID");
                String proCode = rs.getString("PRO_CODE");
                //Integer likeCnt = rs.getInt("LIKE_CNT");
                String brand = rs.getString("BRAND");
                String proName = rs.getString("PRO_NAME");
                String proKorName = rs.getString("PRO_KORNAME");
                Integer price = rs.getInt("PRICE");
                Date launDate = rs.getDate("LAUN_DATE");
                String img1Path = rs.getString("IMG1");

                LikeVO vo = new LikeVO();


                vo.setId(id);
                vo.setProCode(proCode);
                //vo.setLike_cnt(likeCnt);
                vo.setBrand(brand);
                vo.setProName(proName);
                vo.setProKorName(proKorName);
                vo.setPrice(price);
                vo.setLaunDate(launDate);
                vo.setImg1Path(img1Path);
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

    public Integer proLikeCnt(String ProCode){

//        List<LikeVO> list = new ArrayList<>();
        Integer getCnt = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT COUNT (*) FROM LIKE_TB WHERE PRO_CODE = " + "'" + ProCode + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {

                getCnt = rs.getInt("COUNT(*)");

//                LikeVO vo = new LikeVO();
//
//                vo.(getCnt);

//                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getCnt;

        //String sql = "UPDATE PRO_TB SET LIKE_CNT= LIKE_CNT+1 WHERE PRO_CODE = " + "'" + procode + "'";
//        System.out.println("LikeDAO, proLikeCnt 실행");
//        String likeCnt = null;
//        try{
//            conn = Common.getConnection();
//            stmt = conn.createStatement();
//
//            String sql = "SELECT COUNT (*) FROM LIKE_TB WHERE PRO_CODE = " + "'" + ProCode + "'";
//            rs=stmt.executeQuery(sql);
//            likeCnt =rs.getString();
//
//
//            System.out.println(likeCnt);
//            Common.close(stmt);
//            Common.close(conn);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return likeCnt;
    }
}
