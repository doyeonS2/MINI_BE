package com.kh.miniservlet.vo;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class ReplyVO {
    private String id;
    private Integer boardNum; // 글 번호, 변환해줘야 함
    private Integer replyNum; // 댓글 번호, 변환해줘야 함
    private String replyContent; // 댓글 내용
    private Date replyDate; // 댓글 작성일, 변환해줘야 함
}