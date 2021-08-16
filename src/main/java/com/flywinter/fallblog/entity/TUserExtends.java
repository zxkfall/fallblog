package com.flywinter.fallblog.entity;


import lombok.Data;

import java.util.Date;
@Data
public class TUserExtends {

  private String id;
  private String userId;
  private long gender;
  private java.sql.Timestamp birth;
  private String hobby;
  private String address;
  private String field;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;

}
