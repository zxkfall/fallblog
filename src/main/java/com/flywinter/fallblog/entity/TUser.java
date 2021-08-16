package com.flywinter.fallblog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TUser {

  private String id;
  private String nickname;
  private String email;
  private String password;
  private String avatar;
  private String description;
  private long status;
  private long version;
  private Date createTime;
  private Date updateTime;







}
