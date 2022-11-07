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

    // 브랜드 선택 + 정렬 조건
    public List<ItemVO> itemSelect(String reqBrand, String reqSort) {
        List<ItemVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = null;

            // 브랜드 선택 + 정렬 쿼리
            if (reqBrand.equals("ALL")) {
                sql = "SELECT * FROM PRO_TB";
                if (reqSort.equals("NEW_DATE")) {
                    sql = "SELECT * FROM PRO_TB ORDER BY LAUN_DATE DESC";
                } else if (reqSort.equals("HIGH_PRICE")) {
                    sql = "SELECT * FROM PRO_TB ORDER BY PRICE DESC";
                } else sql = "SELECT * FROM PRO_TB ORDER BY PRICE ASC";
            }
            else if(reqBrand.equals("NIKE")) {
                if (reqSort.equals("NEW_DATE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'NIKE\' ORDER BY LAUN_DATE DESC";
                } else if (reqSort.equals("HIGH_PRICE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'NIKE\' ORDER BY PRICE DESC";
                } else sql = "SELECT * FROM PRO_TB where brand = \'NIKE\' ORDER BY PRICE";
            }
            else if(reqBrand.equals("CONVERSE")) {
                if (reqSort.equals("NEW_DATE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'CONVERSE\' ORDER BY LAUN_DATE DESC";
                } else if (reqSort.equals("HIGH_PRICE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'CONVERSE\' ORDER BY PRICE DESC";
                } else sql = "SELECT * FROM PRO_TB where brand = \'CONVERSE\' ORDER BY PRICE";
            }
            else if(reqBrand.equals("ADIDAS")) {
                if (reqSort.equals("NEW_DATE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'ADIDAS\' ORDER BY LAUN_DATE DESC";
                } else if (reqSort.equals("HIGH_PRICE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'ADIDAS\' ORDER BY PRICE DESC";
                } else sql = "SELECT * FROM PRO_TB where brand = \'ADIDAS\' ORDER BY PRICE";
            }
            else if(reqBrand.equals("NEW BALANCE")) {
                if (reqSort.equals("NEW_DATE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'NEW BALANCE\' ORDER BY LAUN_DATE DESC";
                } else if (reqSort.equals("HIGH_PRICE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'NEW BALANCE\' ORDER BY PRICE DESC";
                } else sql = "SELECT * FROM PRO_TB where brand = \'NEW BALANCE\' ORDER BY PRICE";
            }
            else if(reqBrand.equals("VANS")) {
                if (reqSort.equals("NEW_DATE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'VANS\' ORDER BY LAUN_DATE DESC";
                } else if (reqSort.equals("HIGH_PRICE")) {
                    sql = "SELECT * FROM PRO_TB where brand = \'VANS\' ORDER BY PRICE DESC";
                } else sql = "SELECT * FROM PRO_TB where brand = \'VANS\' ORDER BY PRICE";
            }

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String proCode = rs.getString("PRO_CODE");
                String brand = rs.getString("BRAND");
                String proName = rs.getString("PRO_NAME");
                Integer price = rs.getInt("PRICE");
                Date launDate = rs.getDate("LAUN_DATE");
                String imgPath = rs.getString("IMG");

                ItemVO vo = new ItemVO();
                vo.setProCode(proCode);
                vo.setBrand(brand);
                vo.setProName(proName);
                vo.setPrice(price);
                vo.setLaunDate(launDate);
                vo.setImgPath(imgPath);
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


    // 카테고리 필터 함수
    // cmd : 정렬기준, 브랜드, 상품코드
    public List<ItemVO> itemCmdSelect(String cmd) {

        List<ItemVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = null; // sql문을 생성하고 들어오는 인자값 (reqId)에 따라
            // || cmd.equals("LOW_PRICE") || cmd.equals("HIGH_PRICE")

            if(cmd.equals("NEW_DATE")) sql = "SELECT * FROM PRO_TB ORDER BY LAUN_DATE DESC" ;
            else if(cmd.equals("LOW_PRICE")) sql = "SELECT * FROM PRO_TB ORDER BY PRICE " ;
            else if(cmd.equals("HIGH_PRICE")) sql = "SELECT * FROM PRO_TB ORDER BY PRICE DESC" ;

            else if(cmd.equals("NEW BALANCE")) sql = "SELECT * FROM PRO_TB WHERE BRAND = \'NEW BALANCE\' " ;
            else if(cmd.equals("NIKE")) sql = "SELECT * FROM PRO_TB WHERE BRAND = \'NIKE\'" ;
            else if(cmd.equals("ADIDAS")) sql = "SELECT * FROM PRO_TB WHERE BRAND = \'ADIDAS\'" ;
            else if(cmd.equals("CONVERSE")) sql = "SELECT * FROM PRO_TB WHERE BRAND = \'CONVERSE\'" ;
            else if(cmd.equals("VANS")) sql = "SELECT * FROM PRO_TB WHERE BRAND = \'VANS\'" ;

            else sql = "SELECT * FROM PRO_TB WHERE PRO_CODE = " +  "'" + cmd + "'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String proCode = rs.getString("PRO_CODE");
                String brand = rs.getString("BRAND");
                String proName = rs.getString("PRO_NAME");
                Integer price = rs.getInt("PRICE");
                Date launDate = rs.getDate("LAUN_DATE");
                String imgPath = rs.getString("IMG");

                ItemVO vo = new ItemVO();

                vo.setProCode(proCode);
                vo.setBrand(brand);
                vo.setProName(proName);
                vo.setPrice(price);
                vo.setLaunDate(launDate);
                vo.setImgPath(imgPath);
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
