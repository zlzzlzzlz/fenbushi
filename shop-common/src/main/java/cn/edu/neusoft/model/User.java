package cn.edu.neusoft.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shop_user")
public class User {
    @TableId(type = IdType.AUTO)
//mybatis-plus 主键注解
    @IsAutoIncrement
//自增
    @Column(comment = "用户 id")
//对应数据库字段，不配置 name 会直接采用属性名作为字段名 comment 是注解
    private Integer uid;
    @Column(comment = "用户名")
    private String username;
    @Column(comment = "密码")
    private String password;
    @Column(comment = "电话")
    private String telephone;
}
