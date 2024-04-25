package cn.edu.neusoft.Service.fallback;

import cn.edu.neusoft.Service.ProductServiceFeign;
import cn.edu.neusoft.model.Product;
import org.springframework.stereotype.Component;

@Component

public class ProductServiceFallback implements ProductServiceFeign {
    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.setPid(-100);
        product.setPname("下单失败，商品微服务不可用");
        return product;
    }

    @Override
    public String getMessage(String str) {
        return null;
    }
}