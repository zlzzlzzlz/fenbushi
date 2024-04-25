package cn.edu.neusoft.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shop_order")
public class Order {
    @TableId(type = IdType.AUTO)//mybatis-plus 主键注解
    @IsAutoIncrement //自增
    @Column(comment = "订单 id")//对应数据库字段，不配置 name 会直接采用属性名作为字段名 comment 是注解
    private Long oid;
    @Column(comment = "用户 id")
    private Integer uid;
    @Column(comment = "用户名")
    private String username;
    @Column(comment = "商品 id")
    private Integer pid;
    @Column(comment = "商品名称")
    private String pname;
    @Column(comment = "商品单价")
    private Double pprice;
    @Column(comment = "购买数量")
    private Integer number;
    @Column(comment = "订单状态")
    private Integer status;
}