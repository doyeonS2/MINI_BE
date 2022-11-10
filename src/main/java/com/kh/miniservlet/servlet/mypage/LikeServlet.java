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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.kh.miniservlet.common.Common.FRONT_DOMAIN;

@RestController
@CrossOrigin(origins = FRONT_DOMAIN)
public class LikeServlet extends HttpServlet {
    @PostMapping("/LikeServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        StringBuffer sb = Common.reqStringBuff(request);
        JSONObject jsonObj = Common.getJsonObj(sb);

        // String getCmd = (String) jsonObj.get("cmd");
        String getId = (String) jsonObj.get("id");
        System.out.println("받은 아이디 : " + getId);
        //System.out.println("받은 cmd : " + getCmd);

        PrintWriter out = response.getWriter();
        LikeDAO dao = new LikeDAO();

        List<LikeVO> list = dao.LikeInfo(getId);


        JSONArray LikeArray = new JSONArray();
        for (LikeVO e : list) {
            // 자바 객체 생성
            JSONObject LikeInfo = new JSONObject();
            LikeInfo.put("id", e.getId());
            LikeInfo.put("proCode", e.getProCode());
            LikeInfo.put("brand", e.getBrand());
            LikeInfo.put("proName", e.getProName());
            LikeInfo.put("proKorName", e.getProKorName());
            LikeInfo.put("img1Path", e.getImg1Path());

            NumberFormat numberFormat = NumberFormat.getInstance();
            String numToStr = numberFormat.format(e.getPrice());
            LikeInfo.put("price", numToStr);

            DateFormat dateFormat = new SimpleDateFormat("YY/MM/dd");
            String dateToStr = dateFormat.format(e.getLaunDate());
            LikeInfo.put("launDate", dateToStr);

            LikeArray.add(LikeInfo);
        }
        System.out.println(LikeArray);
        out.print(LikeArray);

        //String like_Cnt = (String) jsonObj.get("COUNT(*)");
        //System.out.println(like_Cnt);

        //out.print(like_Cnt);
    }
}
