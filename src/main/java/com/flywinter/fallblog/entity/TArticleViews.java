package com.flywinter.fallblog.entity;


import lombok.Data;

import java.util.Date;
@Data
public class TArticleViews {

  private long id;
  private String articleId;
  private long viewCount;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;



}
