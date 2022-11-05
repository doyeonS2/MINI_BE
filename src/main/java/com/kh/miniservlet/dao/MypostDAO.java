package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MypostDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

public List<BoardDAO> WriteInfo(String id) {

    List<BoardDAO> list = new ArrayList<>();
    try {
        conn = Common.getConnection();
        stmt = conn.createStatement(); // Statement 객체
        String sql = "SELECT * FROM BOARD WHERE ID = " + "'" + id + "'";
        rs = stmt.executeQuery(sql);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
