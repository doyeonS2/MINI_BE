package com.kh.miniservlet.servlet.item;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.ResultDAO;
import com.kh.miniservlet.vo.BoardVO;
import com.kh.miniservlet.vo.ItemVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.kh.miniservlet.common.Common.FRONT_DOMAIN;

@RestController
@CrossOrigin(origins = FRONT_DOMAIN) // 3000번에 대한 cors 허용



public class ResultServlet extends HttpServlet {
    @PostMapping("/ResultServlet") // 이클립스의 서블렛파일에서 쓰는 @WebServlet("/LoginServlet")와 같은 기능
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        response.setCharacterEncoding("utf-8");
        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱

        String getResult = (String) jsonObj.get("result");

        ResultDAO dao = new ResultDAO();
        List<ItemVO> list = dao.researchCheck(getResult);

        PrintWriter out = response.getWriter();
        JSONArray searchArray = new JSONArray();
        for (ItemVO e : list) {
            // 자바 객체 생성
            JSONObject searchInfo = new JSONObject();
            searchInfo.put("proCode", e.getProCode());
            searchInfo.put("brand", e.getBrand());
            searchInfo.put("proKorName", e.getProKorName());
            searchInfo.put("proName", e.getProName());
            searchInfo.put("img1Path", e.getImg1Path());
            searchInfo.put("price", e.getPrice());

            searchArray.add(searchInfo);
        }
        System.out.println(searchArray);
        out.print(searchArray);
    }
}
