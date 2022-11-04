package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MailDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;  // s
    public boolean rePwd(String id, String pwd) {
        int result = 0;
        String sql = "UPDATE MEM_TB SET PASSWORD = ? WHERE ID = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pwd);
            pstmt.setString(2, id);
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
