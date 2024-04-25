package cn.edu.neusoft.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class SentinelExceptionHandlerPage implements BlockExceptionHandler{

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        ResponseData responseData = null;
        if (e instanceof FlowException) {
            responseData = new ResponseData(444,"请求被限流了！");
        } else if (e instanceof DegradeException) {
            responseData = new ResponseData(555,"请求被降级了！");
        } else if (e instanceof ParamFlowException) {
            responseData = new ResponseData(666, "热点参数限流！");
        } else if (e instanceof AuthorityException) {
            responseData = new ResponseData(777,"请求没有权限！");
        } else if (e instanceof SystemBlockException) {
            responseData = new ResponseData(888,"系统负载异常！");
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().println(JSON.toJSONString(responseData));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ResponseData {
    private int code;
    private String message;
}

