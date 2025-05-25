package com.ch.ch20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("usertable")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String uname;
    private String upwd;
    //表示该属性不为数据库表字段，但又是必须使用的
    @TableField(exist = false)
    private String code;
    @TableField(exist = false)
    private String reupwd;
}
