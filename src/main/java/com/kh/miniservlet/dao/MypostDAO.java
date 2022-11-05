package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.BoardVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MypostDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

public List<BoardVO> WriteInfo(String id) {

    List<BoardVO> list = new ArrayList<>();
    try {
        conn = Common.getConnection();
        stmt = conn.createStatement(); // Statement 객체
        String sql = "SELECT * FROM BOARD WHERE ID = " + "'" + id + "'";
        rs = stmt.executeQuery(sql);

        while(rs.next()) {

            Integer docNum = rs.getInt("DOC_NUM");
            Integer category = rs.getInt("CATEGORY");
            String title = rs.getString("TITLE");
            String content = rs.getString("CONTENT");
            String id1 = rs.getString("ID");
            Date writeDate = rs.getDate("WRITE_DATE");

            BoardVO vo = new BoardVO();

            vo.setBoardNum(docNum);
            vo.setCategory(category);
            vo.setTitle(title);
            vo.setBoardContent(content);
            vo.setId(id1);
            vo.setBoardDate(writeDate);
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
