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

// 도연 작업 완료 - 아이디 중복체크
@RestController
@CrossOrigin(origins = "http://localhost:3000") // 3000번에 대한 cors 허용
public class IdCheckServlet extends HttpServlet {
    @PostMapping("/IdCheckServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱

        String getId = (String)jsonObj.get("id");
        SignUpDAO dao = new SignUpDAO();

        boolean isNotReg = dao.IdCheck(getId); // isNotReg = TRUE 가입 안된 경우


        PrintWriter out = response.getWriter();
        JSONObject resJson = new JSONObject();

        if(isNotReg) resJson.put("result", "OK"); // 가입 안되어 있으면 OK
        else resJson.put("result", "NOK"); // 가입되어 있으면 NOK
        out.print(resJson);
    }
}
