package com.kh.miniservlet.servlet.board;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.BoardDAO;
import com.kh.miniservlet.vo.BoardVO;
import org.json.simple.JSONArray;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.kh.miniservlet.common.Common.FRONT_DOMAIN;
import static java.lang.System.out;
import static java.lang.System.setOut;

@RestController
@CrossOrigin(origins = FRONT_DOMAIN)
public class BoardDeleteServlet extends HttpServlet {

    @PostMapping("/BoardDeleteServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        StringBuffer sb = Common.reqStringBuff(request);
        JSONObject jsonObj = Common.getJsonObj(sb);

        // 필요한 모든 값을 넣어준다
        String reqCmd = (String)jsonObj.get("cmd");
        String getDocNum = (String)jsonObj.get("boardNum");
        String getId = (String)jsonObj.get("id");

        // 값들이 잘 들어왔는지 확인
        System.out.println(reqCmd);
        System.out.println(getDocNum);
        System.out.println(getId);




        PrintWriter out = response.getWriter();
        if(!reqCmd.equals("BoardDelete")) {
            JSONObject resJson = new JSONObject();
            resJson.put("result", "NOK");
            out.print(resJson);
            return;
        }


        // println으로 잘 들어왔는지 확인한다
        out.println("cmd : " + reqCmd);

        // 받아온 boardNum을 Integer로 변환한다
        int newBoardNum = Integer.parseInt(getDocNum);



        // DAO에 있는 boardDelete를 사용한 (성공시 ture 반환)
        BoardDAO dao = new BoardDAO();

        System.out.println("whoWriteBoard : " + dao.whoWriteBoard(getDocNum));

        if(!dao.whoWriteBoard(getDocNum).equals(getId)) {
            JSONObject resJson = new JSONObject();
            resJson.put("result", "NOK not Writer");
            out.print(resJson);
            return;
        }



        boolean rstComplete = dao.boardDelete(newBoardNum);

        JSONObject resJson = new JSONObject();
        if(rstComplete) resJson.put("result", "OK");
        else resJson.put("result", "NOK fail to delete");
        out.print(resJson);
    }
}
