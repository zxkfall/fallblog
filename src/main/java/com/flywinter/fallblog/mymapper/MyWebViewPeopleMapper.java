package com.flywinter.fallblog.mymapper;

import com.flywinter.fallblog.dto.WeekView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/23 16:35
 * Description:
 */
@Mapper
public interface MyWebViewPeopleMapper {
    List<WeekView> getRecentWeekView();
}
