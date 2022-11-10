package com.kh.miniservlet.servlet.board;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.BoardDAO;
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
import java.io.UnsupportedEncodingException;

import static com.kh.miniservlet.common.Common.FRONT_DOMAIN;
import static java.lang.System.out;

@RestController
@CrossOrigin(origins = FRONT_DOMAIN)
public class BoardRegServlet extends HttpServlet {

    @PostMapping("/BoardRegServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        StringBuffer sb = Common.reqStringBuff(request);
        JSONObject jsonObj = Common.getJsonObj(sb);

        // 필요한 모든 값을 넣어준다
        String reqCmd = (String) jsonObj.get("cmd");
        String getCategory = (String)jsonObj.get("category");
        String getTitle = (String)jsonObj.get("title");
        String getContent = (String)jsonObj.get("content");
        String getId = (String)jsonObj.get("id");

        // println으로 잘 들어왔는지 확인한다
        System.out.println("cmd : " + reqCmd);
        // 잘못 받아왔을 경우
        if(!reqCmd.equals("boardReg")) {
            PrintWriter out = response.getWriter();
            JSONObject resJson = new JSONObject();
            resJson.put("result", "NOK wrong cmd");
            out.print(resJson);
            return;
        }

        BoardDAO dao = new BoardDAO();

        boolean rstComplete = dao.boardRegister( getCategory, getTitle, getContent, getId);

        PrintWriter out = response.getWriter();
        JSONObject resJson = new JSONObject();
        if(rstComplete) resJson.put("result", "OK");
        else resJson.put("result", "NOK fail to reg");
        out.print(resJson);
    }
}
