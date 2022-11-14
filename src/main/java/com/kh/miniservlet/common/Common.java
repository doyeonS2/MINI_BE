package com.kh.miniservlet.common;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Common {
    public final static String FRONT_DOMAIN = "http://localhost:3000";
    final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final static String ORACLE_ID = "scott";
    final static String ORACLE_PW = "tiger";
    final static String ORACLE_DRV = "oracle.jdbc.driver.OracleDriver";

    public static Connection getConnection() {
        Connection conn = null; // 드라이버 연동을 위해 사용
        try {
            Class.forName(ORACLE_DRV); // 드라이버를 메모리에 로딩
            conn = DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PW); // db 연결
            // db와 연결시 연결 성공 문구 출력
            System.out.println("Connetcion 연결 성공");
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 당시의 호출스택에 있던 메소드의 정보와 예외결과를 화면 맨 위에 출력함
        }
        return conn;
    }
    public static void close(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void close(Statement stmt) {
        try {
            if(stmt != null && stmt.isClosed()) {
                stmt.close();
                System.out.println("Statement 해제 성공");
            }
        } catch (Exception e) {e.printStackTrace();
        }
    }

    public static void close(ResultSet res) {
        try {
            if(res != null && res.isClosed()) {
                res.close();
                System.out.println("Result set 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 서블릿으로부터 입력 받은 내용을 문자열로 변환
    public static StringBuffer reqStringBuff(HttpServletRequest req) {
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader(); // 개행문자를 만나기 전까지 읽음
            while((line = reader.readLine()) != null) sb.append(line);
        } catch (Exception e) {
        }
        return sb;
    }

    // JSON Object 만들기
    public static JSONObject getJsonObj(StringBuffer sb) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject)parser.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
