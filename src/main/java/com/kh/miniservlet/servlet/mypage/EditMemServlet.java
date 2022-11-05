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

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 3000번에 대한 cors 허용
public class EditMemServlet extends HttpServlet {
    @PostMapping("/EditMemServlet") // 이클립스의 서블렛파일에서 쓰는 @WebServlet("/LoginServlet")와 같은 기능
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        response.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정

        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱

        String getId = (String)jsonObj.get("id");
        System.out.println("받은 아이디 : " + getId);
//        String getPwd = (String)jsonObj.get("pwd");
//        String getName = (String)jsonObj.get("name");
//        String getEmail = (String)jsonObj.get("email");
//        String getPhone = (String)jsonObj.get("phone");
//        String getAddr = (String)jsonObj.get("addr");


        //int resState = dao.(getId, getPwd);

        PrintWriter out = response.getWriter();


        EditMemberDAO dao = new EditMemberDAO();

        // 현재 하나의 멤버 객체는 list에 있음
        List<MemberVO> list = dao.MemInfo(getId);

        JSONArray memberArray = new JSONArray();
        for (MemberVO e : list){
            JSONObject memberInfo = new JSONObject();
            memberInfo.put("id", e.getId());
            memberInfo.put("pwd", e.getPwd());
            memberInfo.put("memName", e.getMemName());
            memberInfo.put("email", e.getEmail());
            memberInfo.put("phone", e.getPhone());
            memberInfo.put("addr", e.getAddr());

            memberArray.add(memberInfo);
        }

        System.out.println(memberArray);

        out.print(memberArray);
    }
}
