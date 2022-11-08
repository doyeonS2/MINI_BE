package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.ReplyVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReplyDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    // 댓글 보기(select)
    public List<ReplyVO> replySelect(String reqDocNum) {

        List<ReplyVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            // 여기 if문 이상함!!!!!
            String sql = "SELECT * FROM REPLY WHERE DOC_NUM = " +  reqDocNum;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Integer replyNum = rs.getInt("REPLY_NUM"); // 댓글 번호, 변환해줘야 함
                String id = rs.getString("ID");
                Integer docNum = rs.getInt("DOC_NUM"); // 글 번호, 변환해줘야 함
                String replyContent = rs.getString("REPLY_CONTENTS"); // 댓글 내용
                Date replyDate = rs.getDate("REPLY_DATE");

                ReplyVO vo = new ReplyVO();

                vo.setReplyNum(replyNum);
                vo.setId(id);
                vo.setBoardNum(docNum);
                vo.setReplyContent(replyContent);
                vo.setReplyDate(replyDate);

                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // 댓글 쓰기(insert)
    public boolean boardRegister( String category, String title, String content, String id) {
        int result = 0;
        String sql = "INSERT INTO REPLY VALUES(?, ?, ?, ?,SYSDATE)";
        try {
            // REPLY_NUM, DOC_NUM, ID, REPLY_CONTENTS, REPLY_DATE 총 4개의 컬럼 존재

            // 현재의 가장 큰 boardNum을 가져온다
            BoardDAO dao = new BoardDAO();
            Integer nowBiggestBoardNum = dao.biggestBoardNum();
            // String 으로 받아온 값을 Int형으로 변경해준다
            int newCategory = Integer.parseInt(category);

            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nowBiggestBoardNum + 1);
            pstmt.setInt(2, newCategory);
            pstmt.setString(3, title);
            pstmt.setString(4, content);
            pstmt.setString(5, id);
            result = pstmt.executeUpdate();
            System.out.println("글쓰기 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }

    // 댓글 수정(update)



    // 댓글 삭제(delete)
    public boolean replyDelete(Integer replyNum) {
        int result = 0;
        String sql = "DELETE FROM BOARD WHERE reply_NUM = ? ";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, replyNum);
            result = pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
        if(result == 1) return true;
        else return false;
    }
}