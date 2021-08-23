package com.flywinter.fallblog.mapper;

import com.flywinter.fallblog.entity.TWebViewPeople;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flywinter.fallblog.dto.WeekView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxkfall
 * @since 2021-08-20
 */
@Mapper
public interface TWebViewPeopleMapper extends BaseMapper<TWebViewPeople> {

//    @Select("select a.view_date,ifnull(b.count,0) as count " +
//            "from (" +
//            "         SELECT curdate() as view_date" +
//            "         union all" +
//            "         SELECT date_sub(curdate(), interval 1 day) as view_date" +
//            "         union all" +
//            "         SELECT date_sub(curdate(), interval 2 day) as view_date" +
//            "         union all" +
//            "         SELECT date_sub(curdate(), interval 3 day) as view_date" +
//            "         union all" +
//            "         SELECT date_sub(curdate(), interval 4 day) as view_date" +
//            "         union all" +
//            "         SELECT date_sub(curdate(), interval 5 day) as view_date" +
//            "         union all" +
//            "         SELECT date_sub(curdate(), interval 6 day) as view_date" +
//            "     ) a left join (" +
//            "    select date(create_time) as datetime, count(*) as count" +
//            "    from t_web_view_people" +
//            "    group by date(create_time)" +
//            ") b on a.view_date = b.datetime;")
//    List<WeekView> getRecentWeekView();

}
