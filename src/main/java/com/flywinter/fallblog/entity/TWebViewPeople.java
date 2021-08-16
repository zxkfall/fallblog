package com.flywinter.fallblog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TWebViewPeople {

  private String id;
  private String ip;
  private String device;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;


}
