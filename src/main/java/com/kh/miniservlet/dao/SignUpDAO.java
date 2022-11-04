package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//DB와 연결하는 부분 만들어주기
//노션 DATABASE -> JDBC 구현 순서 참고
public class SignUpDAO {
    private Connection conn = null;
    private Statement stmt = null; // 표준 SQL문을 수행하기 위한 Statement 객체 얻기
    private ResultSet rs = null; // Statement의 수행 결과를 여러행으로 받음(다음에 읽을 게 없을때까지)
    // SQL문을 미리 컴파일해서 재사용하므로 Statement 인터페이스보다 훨씬 빠르게 데이터베이스 작업을 수행
    private PreparedStatement pstmt = null;

    // 회원가입 관련 메소드 만들기

    // 회원가입
    public boolean memberRegister(String id, String pwd, String pwdCheck, String memName, String email, String phone, String addr, String RegDate) {
        int result = 0;
        String sql = "INSERT INTO MEM_TB(ID, PASSWORD, NAME, EMAIL, PHONE, ADDR, JOIN) VALUES(?, ?, ?, ?, ?, ?, ?, SYSDATE)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id); // 첫번째 물음표에 id 들어감
            pstmt.setString(2, pwd); // 두번째에는 pwd
            pstmt.setString(3, pwdCheck);
            pstmt.setString(4, memName);
            pstmt.setString(5, email);
            pstmt.setString(6, phone);
            pstmt.setString(7, addr);
            pstmt.setString(8, RegDate);

            result = pstmt.executeUpdate(); // 디비에 값을 저장
            System.out.println("회원 가입 DB 결과 확인 : " + result);

        } catch (Exception e) { // 예외 처리 구문
            e.printStackTrace(); // 에러 위치 찾아줌(맨 마지막에 불려진게 맨 위로 올라감 => 에러 부분이 맨 위에 있다는 것)
        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }

    // 아이디 중복확인
    public boolean IdCheck(String id) {
        boolean isNotReg = false; // 기본 값이 false(가입된 경우)
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEM_TB WHERE ID = " + "'" + id +"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()) isNotReg = false; // 가입된 경우
            else isNotReg = true; // 가입 안된 경우
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return isNotReg; // 가입 되어 있으면 false, 가입이 안되어 있으면 true
    }
}
