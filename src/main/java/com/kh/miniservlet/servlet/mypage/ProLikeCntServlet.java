package com.kh.miniservlet.servlet.mypage;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.LikeDAO;
import com.kh.miniservlet.vo.BoardVO;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProLikeCntServlet extends HttpServlet {
    @PostMapping("/ProLikeCntServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 상품에 대한 관심상품 갯수
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        StringBuffer sb = Common.reqStringBuff(request);
        JSONObject jsonObj = Common.getJsonObj(sb);

        String getProCode = (String) jsonObj.get("proCode");
        System.out.println("받은 상품 : " + getProCode);

        PrintWriter out = response.getWriter();


        //String result = dao.proLikeCnt(getProCode);
        //LikeVO vo = new LikeVO();
        JSONObject likeObject = new JSONObject();
        //likeObject.put("COUNT(*)", result);

        LikeDAO dao = new LikeDAO();
        int result = dao.proLikeCnt(getProCode);

//        JSONArray proLikeArray = new JSONArray();
//        for (LikeVO e : list) {
//            JSONObject LikeInfo = new JSONObject();
//            LikeInfo.put("proCode", e.getProCode());
//
//            proLikeArray.add(LikeInfo);
//        }
        System.out.println(result);
        likeObject.put("totalCnt",result);



        out.print(likeObject);

        //String like_Cnt = (String) jsonObj.get("COUNT(*)");
        //System.out.println(like_Cnt);

        //out.print(like_Cnt);
    }
}
