package com.flywinter.fallblog.entity;


import lombok.Data;

import java.util.Date;
@Data
public class TImages {

  private String id;
  private String url;
  private String path;
  private String name;
  private String type;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;


}
