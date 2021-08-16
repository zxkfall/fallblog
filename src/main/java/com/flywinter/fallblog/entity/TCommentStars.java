package com.flywinter.fallblog.entity;


import lombok.Data;

import java.util.Date;
@Data
public class TCommentStars {

  private String id;
  private String commentId;
  private String email;
  private String ip;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;



}
