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

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PwdServlet extends HttpServlet {
    @PostMapping("/PwdServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱

        String getId = (String)jsonObj.get("id");
        String getEmail = (String)jsonObj.get("email");

        LoginDAO dao = new LoginDAO();
        Boolean resState = dao.findPWD(getId, getEmail);

        // 이제 뭐해줘야 해? => 서블릿 응답!
        PrintWriter out = response.getWriter();
        JSONObject resJson = new JSONObject(); // json 객체

        if(resState) resJson.put("result", "OK");
        else{
            resJson.put("result", "NO");
        }
        out.print(resJson);
    }

}
