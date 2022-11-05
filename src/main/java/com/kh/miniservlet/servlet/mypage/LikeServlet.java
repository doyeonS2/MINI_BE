package com.kh.miniservlet.servlet.mypage;

import com.kh.miniservlet.common.Common;
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
public class LikeServlet extends HttpServlet {
    @PostMapping("/LikeServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        StringBuffer sb = Common.reqStringBuff(request);
        JSONObject jsonObj = Common.getJsonObj(sb);

        String getId = (String) jsonObj.get("id");
        System.out.println("받은 아이디 : " + getId);

        PrintWriter out = response.getWriter();
        LikeDAO dao = new LikeDAO();

        // 현재 하나의 멤버 객체는 list에 있음
        List<LikeVO> list = dao.LikeInfo(getId);

        JSONArray likeArray = new JSONArray();
        for (LikeVO e : list) {
            JSONObject likeInfo = new JSONObject();
            likeInfo.put("id", e.getId());
            likeInfo.put("proCode", e.getProCode());
            likeInfo.put("likeCnt", e.getLike_cnt());

            likeArray.add(likeInfo);
        }
        System.out.println(likeArray);

        out.print(likeArray);
    }
}
