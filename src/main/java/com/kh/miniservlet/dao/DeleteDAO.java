package com.kh.miniservlet.dao;
import com.kh.miniservlet.common.Common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteDAO {

    // 도연 - 회원탈퇴 작업중..
    private Connection conn = null;
    //private Statement stmt = null; // 표준 SQL문을 수행하기 위한 Statement 객체 얻기
    //private ResultSet rs = null; // Statement의 수행 결과를 여러행으로 받음(다음에 읽을 게 없을때까지)
    // SQL문을 미리 컴파일해서 재사용하므로 Statement 인터페이스보다 훨씬 빠르게 데이터베이스 작업을 수행
    private PreparedStatement pstmt = null;

    public boolean memberDelete(String id) {
        int result = 0;
        String sql = "DELETE FROM MEM_TB WHERE ID = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            result = pstmt.executeUpdate();
            System.out.println("회원 탈퇴 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }
}
