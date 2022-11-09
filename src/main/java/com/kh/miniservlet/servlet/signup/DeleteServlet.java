package com.kh.miniservlet.servlet.signup;
import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.DeleteDAO;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

// 도연 - 회원탈퇴 작업 완료

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DeleteServlet extends HttpServlet {
    @PostMapping("/DeleteServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        StringBuffer sb = Common.reqStringBuff(request);
        JSONObject jsonObj = Common.getJsonObj(sb);
        String getId = (String)jsonObj.get("id");
        String getPwd = (String)jsonObj.get("pwd");

        DeleteDAO dao = new DeleteDAO();
        boolean rstComplete = false;
        try {
            rstComplete = dao.memberDelete(getId, getPwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        JSONObject resJson = new JSONObject();
        if(rstComplete) resJson.put("result", "OK");
        else resJson.put("result", "NOK");
        out.print(resJson);
    }
}