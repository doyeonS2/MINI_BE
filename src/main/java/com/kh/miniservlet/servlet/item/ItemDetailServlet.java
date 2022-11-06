package com.kh.miniservlet.servlet.item;

import com.kh.miniservlet.common.Common;
import com.kh.miniservlet.dao.ItemDAO;
import com.kh.miniservlet.dao.ItemDetailDAO;
import com.kh.miniservlet.vo.ItemVO;
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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ItemDetailServlet extends HttpServlet {
    @PostMapping("/ItemDetailServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 한글 깨짐 방지를 위해서 설정
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // 요청 메시지 받기
        StringBuffer sb = Common.reqStringBuff(request);
        // 요청 받은 메시지 JSON 파싱
        JSONObject jsonObj = Common.getJsonObj(sb);

        String reqCmd = (String)jsonObj.get("cmd");
        String reqCode = (String)jsonObj.get("proCode");

        System.out.println("명령어 : " + reqCmd);
        System.out.println("상품코드 : " + reqCode);

        PrintWriter out = response.getWriter();
        if(!reqCmd.equals("ItemDetailInfo")) {
            JSONObject resJson = new JSONObject();
            resJson.put("result", "NOK");
            out.print(resJson);
            return;
        }
        ItemDetailDAO dao = new ItemDetailDAO();
        List<ItemVO> list = dao.itemDetailSelect(reqCode);

        JSONArray itemArray = new JSONArray();
        for (ItemVO e : list) {
            JSONObject itemDetailInfo = new JSONObject();
            itemDetailInfo.put("PRO_CODE", e.getProCode());
            itemDetailInfo.put("BRAND", e.getBrand());
            itemDetailInfo.put("PRO_NAME", e.getProName());

            NumberFormat numberFormat = NumberFormat.getInstance();
            String numToStr = numberFormat.format(e.getPrice());
            itemDetailInfo.put("PRICE", numToStr);

            DateFormat dateFormat = new SimpleDateFormat("YY/MM/dd");
            String dateToStr = dateFormat.format(e.getLaunDate());
            itemDetailInfo.put("LAUN_DATE", dateToStr);
            itemArray.add(itemDetailInfo);
        }
        out.print(itemArray);
    }
}
