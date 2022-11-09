package com.kh.miniservlet.servlet.mypage;

import com.kh.miniservlet.common.Common;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LikeCntServlet extends HttpServlet {

    @PostMapping("/LikeCntServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 상품을 눌렀을 때 cnt값 반영되도록
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        StringBuffer sb = Common.reqStringBuff(request);
        JSONObject jsonObj = Common.getJsonObj(sb);


    }
}
