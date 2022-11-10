package com.kh.miniservlet.servlet.reply;


import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.BoardDAO;
import com.kh.miniservlet.dao.ReplyDAO;
import com.kh.miniservlet.vo.BoardVO;
import com.kh.miniservlet.vo.ReplyVO;
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

@RestController
@CrossOrigin(origins = FRONT_DOMAIN)
public class ReplyListServlet extends HttpServlet {
    @PostMapping("/ReplyListServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        StringBuffer sb = Common.reqStringBuff(request);
        JSONObject jsonObj = Common.getJsonObj(sb);

        // 필요한 모든 값을 넣어준다
        String reqCmd = (String) jsonObj.get("cmd"); // 필요??
        String getId = (String)jsonObj.get("id");
        String getReplyNum = (String)jsonObj.get("replyNum");
        String getDockNum = (String)jsonObj.get("dockNum");
        String getReContents = (String)jsonObj.get("replyContents");
        String getReDate = (String)jsonObj.get("replyDate");

        // println으로 잘 들어왔는지 확인한다
        System.out.println("cmd : " + reqCmd);

        // 잘못 받아왔을 경우
        if(!reqCmd.equals("replyReg")) {
            PrintWriter out = response.getWriter();
            JSONObject resJson = new JSONObject();
            resJson.put("result", "NOK wrong cmd");
            out.print(resJson);
            return;
        }

        ReplyDAO dao = new ReplyDAO();

        List<ReplyVO> list = dao.replySelect(getDockNum);
        PrintWriter out = response.getWriter();
        JSONArray replyArray = new JSONArray();
        for (ReplyVO e : list) {
            // 자바 객체 생성
            JSONObject replyInfo = new JSONObject();
            replyInfo.put("id", e.getId());
            replyInfo.put("boardNum", e.getBoardNum());
            replyInfo.put("replyNum", e.getReplyNum());
            replyInfo.put("replyContent", e.getReplyContent());
            DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
            String dateToStr = dateFormat.format(e.getReplyDate());
            replyInfo.put("replyDate", dateToStr);
            replyArray.add(replyInfo);
        }
        System.out.println(replyArray);
        out.print(replyArray);
    }
}