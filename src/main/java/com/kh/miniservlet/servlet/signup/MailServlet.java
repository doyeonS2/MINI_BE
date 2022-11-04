package com.kh.miniservlet.servlet.signup;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.LoginDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 3000번에 대한 cors 허용

public class MailServlet {
    @PostMapping("/MailServlet") // 이클립스의 서블렛파일에서 쓰는 @WebServlet("/LoginServlet")와 같은 기능
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱

        // 수정해야 함
        String getId = (String)jsonObj.get("email");
        String getPwd = (String)jsonObj.get("pwd");

        LoginDAO dao = new LoginDAO();
        int resState = dao.loginCheck(getId, getPwd);

        PrintWriter out = response.getWriter();
        JSONObject resJson = new JSONObject();

        resJson.put("result", resState);
        out.print(resJson);





    }

}
