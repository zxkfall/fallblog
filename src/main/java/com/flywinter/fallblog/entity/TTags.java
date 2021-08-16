package com.flywinter.fallblog.entity;


import lombok.Data;

import java.util.Date;
@Data
public class TTags {

  private long id;
  private String name;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;



}
