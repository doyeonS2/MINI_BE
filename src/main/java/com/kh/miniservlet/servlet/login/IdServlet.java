package com.kh.miniservlet.servlet.login;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.LoginDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.kh.miniservlet.common.Common.FRONT_DOMAIN;

@RestController
@CrossOrigin(origins = FRONT_DOMAIN) // 3000번에 대한 cors 허용

public class IdServlet extends HttpServlet {
    @PostMapping("/IdServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱

//        String getName = (String)jsonObj.get("memName");
        String getMail = (String)jsonObj.get("email");

        LoginDAO dao = new LoginDAO();
        String resId = dao.findID(getMail); // 왜 boolean타입이 아니야? => 참과 거짓이 필요한게 아니라 id를 반환 받아야하니까!
        System.out.println("resId : " + resId);

        PrintWriter out = response.getWriter(); // => http요청에 대한 서블릿 응답
        JSONObject resJson = new JSONObject(); // json 객체

        if(resId.equals("NONE")) resJson.put("result", "NOK");
        else {
            resJson.put("result", "OK");
            resJson.put("id", resId);
        }
        out.print(resJson);
    }
}
