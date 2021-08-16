package com.flywinter.fallblog.entity;


import lombok.Data;

import java.util.Date;
@Data
public class TArticleStars {
  private String id;
  private String articleId;
  private String ip;
  private String device;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;


}
