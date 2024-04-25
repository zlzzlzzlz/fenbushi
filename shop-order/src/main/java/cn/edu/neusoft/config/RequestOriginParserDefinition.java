package cn.edu.neusoft.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.alibaba.druid.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//@Component
public class RequestOriginParserDefinition implements RequestOriginParser {
    //定义区分来源：本质作用是通过 HttpServletRequest 对象获取到来源标识，比
    //将标识返回给 sentinel 的“流控应用”
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String serviceName = httpServletRequest.getParameter("serviceName");
        if(StringUtils.isEmpty(serviceName)){
            throw new RuntimeException("serviceName is empty");
        }
        return serviceName;
    }
}

