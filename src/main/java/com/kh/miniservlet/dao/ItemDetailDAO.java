package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.ItemVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ItemDetailDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    //
    public List<ItemVO> itemDetailSelect(String reqCode) {
        List<ItemVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM PRO_TB WHERE PRO_CODE = " + "'" + reqCode + "'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String proCode = rs.getString("PRO_CODE");
                String brand = rs.getString("BRAND");
                String proName = rs.getString("PRO_NAME");
                Integer price = rs.getInt("PRICE");
                Date launDate = rs.getDate("LAUN_DATE");

                ItemVO vo = new ItemVO();
                vo.setProCode(proCode);
                vo.setBrand(brand);
                vo.setProName(proName);
                vo.setPrice(price);
                vo.setLaunDate(launDate);
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
