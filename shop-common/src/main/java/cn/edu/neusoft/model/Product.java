package cn.edu.neusoft.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shop_product")
public class Product {
    @TableId(type = IdType.AUTO)//mybatis-plus 主键注解
    @IsAutoIncrement //自增
    @IsKey //actable 主键注解
    @Column(comment = "商品 id")//对应数据库字段，不配置 name 会直接采用属性名作为字段名 comment 是

    private Integer pid;
    @Column(comment="商品名称")
    private String pname;
    @Column(comment="商品价格")
    private Double pprice;
    @Column(comment="库存")
    private Integer stock;
}