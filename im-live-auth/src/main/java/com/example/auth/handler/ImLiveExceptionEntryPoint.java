package com.example.auth.handler;

import com.example.auth.constant.SecurityConstants;
import com.example.auth.util.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.example.auth.constant.SecurityConstants.LOGIN_URL;
@Slf4j
public class ImLiveExceptionEntryPoint implements AuthenticationEntryPoint {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (SecurityConstants.AUTHORIZE_PATH.equals(request.getServletPath())) {
            StringBuffer requestUrl = request.getRequestURL();
            if (!ObjectUtils.isEmpty(request.getQueryString())) {
                requestUrl.append("?").append(request.getQueryString());
            }
            // 绝对路径在重定向前添加target参数
            String targetParameter = URLEncoder.encode(requestUrl.toString(), StandardCharsets.UTF_8);
            String targetUrl = LOGIN_URL + "?target=" + targetParameter;
            log.debug("重定向至前后端分离的登录页面：{}", targetUrl);
            this.redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{
            SecurityUtils.exceptionHandler(request,response,authException);
        }
    }
}
