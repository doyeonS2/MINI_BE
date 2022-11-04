package com.kh.miniservlet.dao;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.vo.ItemVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ItemDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    // 전체브랜드 or 선택브랜드
    public List<ItemVO> itemSelect(String reqBrand) {
        List<ItemVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = null;

//			// 브랜드 선택 쿼리
            if (reqBrand.equals("ALL")) sql = "SELECT * FROM PRO_TB";
            else sql = "SELECT * FROM PRO_TB WHERE BRAND = " + "'" + reqBrand + "'";
//
//			// 브랜드 선택 + 정렬 쿼리
//			if (reqBrand.equals("ALL")) {
//				sql = "SELECT * FROM PRO_TB";
//				if (reqSort.equals("NEW_DATE")) {
//					sql = "SELECT * FROM PRO_TB ORDER BY LAUN_DATE DESC";
//				} else if (reqSort.equals("HIGH_PRICE")) {
//						sql = "SELECT * FROM PRO_TB ORDER BY PRICE DESC";
//				} else sql = "SELECT * FROM PRO_TB ORDER BY PRICE ASC";
//			}
//			else sql = "SELECT * FROM PRO_TB WHERE BRAND = " + "'" + reqBrand + "'"; {
//				if (reqSort.equals("NEW_DATE")) {
//					sql = "SELECT * FROM PRO_TB ORDER BY LAUN_DATE DESC";
//				} else if (reqSort.equals("HIGH_PRICE")) {
//						sql = "SELECT * FROM PRO_TB ORDER BY PRICE DESC";
//				} else sql = "SELECT * FROM PRO_TB ORDER BY PRICE ASC";
//			}

//			if (reqBrand.equals("ALL")) {
//				sql = "SELECT * FROM PRO_TB ORDER BY LAUN_DATE DESC";
//			} else sql = "SELECT * FROM PRO_TB WHERE BRAND = " + "'" + reqBrand + "'";

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
