package com.flywinter.fallblog.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/23 13:28
 * Description:
 */
@Data
public class WeekView {
    private LocalDateTime viewDate;
    private Integer count;
}
