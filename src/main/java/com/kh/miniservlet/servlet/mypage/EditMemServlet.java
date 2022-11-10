package com.kh.miniservlet.servlet.mypage;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.EditMemberDAO;
import com.kh.miniservlet.vo.MemberVO;
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
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

import static com.kh.miniservlet.common.Common.FRONT_DOMAIN;

@RestController
@CrossOrigin(origins = FRONT_DOMAIN) // 3000번에 대한 cors 허용
public class EditMemServlet extends HttpServlet {
    @PostMapping("/EditMemServlet") // 이클립스의 서블렛파일에서 쓰는 @WebServlet("/LoginServlet")와 같은 기능
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        response.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정

        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱

        String reqCmd = (String)jsonObj.get("cmd");
        String getId = (String)jsonObj.get("id");
        String getPwd = (String)jsonObj.get("pwd");
        String getName = (String)jsonObj.get("name");
        String getEmail = (String)jsonObj.get("email");
        String getPhone = (String)jsonObj.get("phone");
        String getAddr = (String)jsonObj.get("addr");


        //int resState = dao.(getId, getPwd);

        PrintWriter out = response.getWriter();
        if(!reqCmd.equals("EditMem")) {
            JSONObject resJson = new JSONObject();
            resJson.put("result", "NOK wrong CMD");
            out.print(resJson);
            return;
        }


        EditMemberDAO dao = new EditMemberDAO();

        System.out.println((getId + getPwd + getName + getEmail + getPhone + getAddr));

        // 현재 하나의 멤버 객체는 list에 있음
        // List<MemberVO> list = dao.MemInfo(getId);
        boolean rstComplete = dao.editInfo(getId, getPwd, getName, getEmail,getPhone,getAddr);


        JSONObject resJson = new JSONObject();
        if(rstComplete) resJson.put("result", "OK");
        else resJson.put("result", "NOK fail to Edit");
        out.print(resJson);


    }
}
