package com.ch.ch20.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@TableName("cardtable")
public class Card {
    @TableId(value="id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String telephone;
    private String email;
    private String company;
    private String post;
    private String address;
    //表示该属性不是数据库表字段，但又是必需使用的
    @TableField(exist = false)
    private MultipartFile logo;
    private String logoName;
    private Integer userId;
}
