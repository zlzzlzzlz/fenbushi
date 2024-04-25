package cn.edu.neusoft.Service.fallback;

import cn.edu.neusoft.Service.ProductServiceFeign;
import cn.edu.neusoft.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductServiceFallbackFatory implements FallbackFactory<ProductServiceFeign> {
    @Override
    public ProductServiceFeign create(Throwable cause) {
        return new ProductServiceFeign() {
            @Override
            public Product findByPid(Integer pid) {
                log.error("{}", cause);
                Product product = new Product();
                product.setPid(-100);
                product.setPname("下单失败，商品微服务不可用");
                return product;
            }

            @Override
            public String getMessage(String str) {
                return null;
            }
        };
    }



}