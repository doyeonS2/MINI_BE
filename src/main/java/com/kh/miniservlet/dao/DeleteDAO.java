package com.kh.miniservlet.dao;
import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.MemberVO;
import java.sql.*;

// 도연 - 회원탈퇴 작업 완료

public class DeleteDAO {
    private Connection conn = null;
    private Statement stmt = null; // 표준 SQL문을 수행하기 위한 Statement 객체 얻기
    private ResultSet rs = null; // Statement의 수행 결과를 여러행으로 받음(다음에 읽을 게 없을때까지)
    // SQL문을 미리 컴파일해서 재사용하므로 Statement 인터페이스보다 훨씬 빠르게 데이터베이스 작업을 수행
    private PreparedStatement pstmt = null;

    public boolean memberDelete(String id, String pwd) throws SQLException {
        int result = 0;
        String newPwd = null;
        String sqlGetPwd = "SELECT PASSWORD FROM MEM_TB WHERE ID = " + "'" + id + "'";
        String sqlDel = "DELETE FROM MEM_TB WHERE ID = " + "'" + id + "'";

        conn = Common.getConnection();
        stmt = conn.createStatement();

        rs = stmt.executeQuery(sqlGetPwd);

        while (rs.next()) {
            newPwd = rs.getString("PASSWORD");
        }

        if(newPwd.equals(pwd)) {
            try {
                System.out.println("패스워드가 일치 합니다");
                conn = Common.getConnection();
                pstmt = conn.prepareStatement(sqlDel);
                result = pstmt.executeUpdate();
            } catch(Exception e) {
                e.printStackTrace();
            }
            Common.close(pstmt);
            Common.close(conn);
            if(result == 1) return true;
            else return false;

        } else return false;
    }
}