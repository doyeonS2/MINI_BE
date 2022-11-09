package com.kh.miniservlet.servlet.mypage;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.LikeCntDAO;
import com.kh.miniservlet.dao.LikeDAO;
import com.kh.miniservlet.vo.LikeVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LikeCntServlet extends HttpServlet {

    @PostMapping("/LikeCntServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 상품을 눌렀을 때 cnt값 반영되도록
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        StringBuffer sb = Common.reqStringBuff(request);
        JSONObject jsonObj = Common.getJsonObj(sb);

        String getId = (String) jsonObj.get("id");
        String getproCode = (String) jsonObj.get("proCode");

        System.out.println("받아온 상품 코드 : " + getproCode);
        System.out.println("받아온 아이디 : " + getId);

        PrintWriter out = response.getWriter();
        LikeCntDAO dao = new LikeCntDAO();

        List<LikeVO> list = dao.LikeCnt(getproCode);

        JSONArray LikeCntArray = new JSONArray();
        for(LikeVO e : list) {
            JSONObject LikeCnt = new JSONObject();
            LikeCnt.put("proCode", e.getProCode());

            LikeCntArray.add(LikeCnt);
        }
        System.out.println(LikeCntArray);
        out.print(LikeCntArray);
    }
}