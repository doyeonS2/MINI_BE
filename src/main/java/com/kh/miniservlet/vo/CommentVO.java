package com.kh.miniservlet.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CommentVO {
    private Integer commentNum;
    private Integer boardNum;
    private String id;
    private String content;
    private Date date;
}
