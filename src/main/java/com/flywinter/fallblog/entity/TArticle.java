package com.flywinter.fallblog.entity;


import lombok.Data;

import java.util.Date;
@Data
public class TArticle {

  private String id;
  private String title;
  private String userId;
  private String description;
  private String firstImage;
  private long categoryId;
  private String tags;
  private String content;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;


}
