package com.kh.miniservlet.servlet.signup;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.SignUpDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 도연 작업중 - 회원가입

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 3000번에 대한 cors 허용
public class SignUpServlet extends HttpServlet {
    @PostMapping("/SignUpServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱
        String getId = (String)jsonObj.get("id");
        String getPwd = (String)jsonObj.get("pwd");
        String getMemName = (String)jsonObj.get("memName");
        String getEmail = (String)jsonObj.get("email");
        String getPhone = (String)jsonObj.get("phone");
        String getAddr = (String)jsonObj.get("addr");

        System.out.println("주소 : " + getAddr);


        SignUpDAO dao = new SignUpDAO();
        boolean rstComplete = dao.memberRegister(getId, getPwd, getMemName, getEmail, getPhone, getAddr); // memberRegister : DAO라는 객체에 있는 메소드(MemberDAO 파일에 메소드 만들어야 함)

        PrintWriter out = response.getWriter();
        JSONObject resJson = new JSONObject();
        if(rstComplete) resJson.put("result", "OK");
        else resJson.put("result", "NOK");
        out.print(resJson);


    }
}
