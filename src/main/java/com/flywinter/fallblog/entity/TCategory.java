package com.flywinter.fallblog.entity;


import lombok.Data;

import java.util.Date;
@Data
public class TCategory {

  private long id;
  private String name;
  private String description;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;






}
