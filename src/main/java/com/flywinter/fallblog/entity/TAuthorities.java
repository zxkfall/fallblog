package com.flywinter.fallblog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TAuthorities {

  private long id;
  private String name;
  private String description;
  private boolean enable;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;


}
