package com.dm.ershops.home.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    private String phone;
    private String username;
    private String password;
    private String qq;
    private String createAt;
    private int goods_num;
    private int power;
    private int lastLogin;
    private int status;
}
