package com.kh.miniservlet.servlet.mypage;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.BoardDAO;
import com.kh.miniservlet.dao.EditMemberDAO;
import com.kh.miniservlet.dao.MypostDAO;
import com.kh.miniservlet.vo.BoardVO;
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
@CrossOrigin(origins = "http://localhost:3000")
public class MypostServlet extends HttpServlet {
    @PostMapping("/MypostServlet") // 이클립스의 서블렛파일에서 쓰는 @WebServlet("/LoginServlet")와 같은 기능
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정
        response.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해서 설정

        StringBuffer sb = Common.reqStringBuff(request); // 요청 메시지 받기
        JSONObject jsonObj = Common.getJsonObj(sb); // 요청 받은 메시지 JSON 파싱

        String getId = (String)jsonObj.get("id");
        System.out.println("받은 아이디 : " + getId);

        PrintWriter out = response.getWriter();
        MypostDAO dao = new MypostDAO();

        // 현재 하나의 멤버 객체는 list에 있음
        List<BoardVO> list = dao.WriteInfo(getId);

        JSONArray mypostArray = new JSONArray();
        for (BoardVO e : list){
            JSONObject writeInfo = new JSONObject();
            writeInfo.put("DOC_NUM", e.getBoardNum());
            writeInfo.put("CATEGORY", e.getCategory());
            writeInfo.put("TITLE", e.getTitle());
            writeInfo.put("CONTENT", e.getBoardContent());
            writeInfo.put("id", e.getId());

            mypostArray.add(writeInfo);
        }

        System.out.println(mypostArray);

        out.print(mypostArray);
    }
}
