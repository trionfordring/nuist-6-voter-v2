package icu.fordring.voter.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fordring
 * @Description 用于测试的拦截器
 * @date 2020/6/7 14:54
 */
@ConditionalOnProperty("app.dev.enable")
@Component
public class TestInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(TestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("收到请求：{} {} content-type:{}", request.getMethod(), request.getRequestURI(), request.getContentType());
        return true;
    }
}

