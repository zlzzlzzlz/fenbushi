package cn.edu.neusoft.Controller;

import cn.edu.neusoft.Service.ProductService;
import cn.edu.neusoft.model.Product;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/product/{pid}")
    public Product getProductById(@PathVariable("pid") Integer pid){
        log.info("查询{}商品信息",pid);
        Product product = productService.getProductById(pid);
        log.info("商品信息查询成功，内容为：{}", JSON.toJSONString(product));
        return product;
    }
    @RequestMapping("/product/message/{str}")
    public String getMessage(@PathVariable("str") String str){
        log.info("参数信息：{}",str);
        return "ProductController："+str;
    }


    @RequestMapping("/product/api1/demo1")
    public String demo1(){
        return "demo1";
    }

    @RequestMapping("/product/api1/demo2")
    public String demo2(){
        return "demo2";
    }

    @RequestMapping("/product/api2/demo1")
    public String demo3(){
        return "demo3";
    }

    @RequestMapping("/product/api2/demo2")
    public String demo4(){
        return "demo4";
    }


}
//ProductService