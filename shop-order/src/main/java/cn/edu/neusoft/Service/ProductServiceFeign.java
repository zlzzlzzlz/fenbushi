package cn.edu.neusoft.Service;

import cn.edu.neusoft.Service.fallback.ProductServiceFallback;
import cn.edu.neusoft.Service.fallback.ProductServiceFallbackFatory;
import cn.edu.neusoft.config.FeignConfiguration;
import cn.edu.neusoft.model.Product;
import cn.edu.neusoft.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(value = "service-product", configuration = FeignConfiguration.class, fallback = ProductServiceFallback.class)
@FeignClient(value = "service-product", configuration = FeignConfiguration.class, fallbackFactory = ProductServiceFallbackFatory.class)
public interface ProductServiceFeign {
    @RequestMapping("/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
    @RequestMapping("/product/message/{str}")
    String getMessage(@PathVariable("str") String str);
}


