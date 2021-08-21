package com.flywinter.fallblog.entity;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxkfall
 * @since 2021-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TArticle extends Model<TArticle> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String userId;

    private String description;

    private String firstImage;

    private Integer categoryId;

    private String tags;

    private String content;

    /**
     * 1状态,1为可用,0为不可用
     */
    private Integer status;

    @Version
    private Long version = 1L;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
