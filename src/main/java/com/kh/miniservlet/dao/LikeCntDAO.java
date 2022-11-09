package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.LikeVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LikeCntDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    public List<LikeVO> LikeCnt(String ProCode) {
        List<LikeVO> list = new ArrayList<>();

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM LIKE_TB WHERE PRO_CODE = 'MR993GL'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String id1 = rs.getString("ID");
                String proCode = rs.getString("PRO_CODE");
                //Integer likeCnt = rs.getInt("LIKE_CNT");

                LikeVO vo = new LikeVO();


                vo.setId(id1);
                vo.setProCode(proCode);
                //vo.setLike_cnt(likeCnt);
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

