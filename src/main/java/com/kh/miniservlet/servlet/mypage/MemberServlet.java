package com.kh.miniservlet.servlet.mypage;


import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.MemDAO;
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
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 3000번에 대한 cors 허용
public class MemberServlet extends HttpServlet {
    @PostMapping("/MemberServlet") // 이클립스의 서블렛파일에서 쓰는 @WebServlet("/LoginServlet")와 같은 기능
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {

        System.out.println("MemberServlet 실행");
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        response.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱

        String getId = (String)jsonObj.get("id");
        String getCmd = (String)jsonObj.get("cmd");
        PrintWriter out = response.getWriter(); // => http요청에 대한 서블릿 응답


        if(!getCmd.equals("userEmail")) {
            JSONObject resJson = new JSONObject();
            resJson.put("result", "NOK wrong CMD");
            out.print(resJson);
            return;
        }

        MemDAO dao = new MemDAO();
        //String resEmail = dao.memInfo(getId); // 왜 boolean타입이 아니야? => 참과 거짓이 필요한게 아니라 id를 반환 받아야하니까!

        // out.println("resEmail : " + resEmail);
        System.out.println("들어온 id : " + getId);
        List<MemberVO> list = dao.memInfo(getId);
        System.out.println("memInfo결과 : " + dao.memInfo(getId));
        // 제이슨 형식 어레이 생성
        JSONArray memberArray = new JSONArray();
        for (MemberVO e : list) {
            // 자바 객체 생성
            JSONObject memberInfo = new JSONObject();
            memberInfo.put("email", e.getEmail());
            memberInfo.put("name", e.getMemName());



            memberArray.add(memberInfo);
        }
        System.out.println(memberArray);
        out.print(memberArray);

//        JSONObject resJson = new JSONObject(); // json 객체
//
//
//        resJson.put("email",resEmail);



//        if(resEmail.equals("NONE")) resJson.put("result", "NOK");
//        else {
//            resJson.put("result", "OK");
//            resJson.put("id", resEmail);
//        }



//        out.print(resJson);
        }
    }
