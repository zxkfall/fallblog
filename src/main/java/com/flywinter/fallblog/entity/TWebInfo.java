package com.flywinter.fallblog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TWebInfo {

  private String id;
  private String infoKey;
  private String infoValue;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;




}
