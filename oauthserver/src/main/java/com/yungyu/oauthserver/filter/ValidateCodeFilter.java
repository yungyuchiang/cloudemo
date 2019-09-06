package com.yungyu.oauthserver.filter;

import com.yungyu.oauthserver.controller.ValidateController;
import com.yungyu.oauthserver.exception.ValidateCodeException;
import com.yungyu.oauthserver.untils.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Spring Security实际上是由许多过滤器组成的过滤器链，处理用户登录逻辑的过滤器为UsernamePasswordAuthenticationFilter，而验证码校验过程应该是在这个过滤器之前的，即只有验证码校验通过后采去校验用户名和密码。由于Spring Security并没有直接提供验证码校验相关的过滤器接口，所以我们需要自己定义一个验证码校验的过滤器ValidateCodeFilter
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.endsWithIgnoreCase("/login", request.getRequestURI()) && StringUtils.endsWithIgnoreCase(request.getMethod(), "post")) {
            try {
                validateCode(request);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validateCode (HttpServletRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) request.getSession().getAttribute(ValidateController.SESSION_KEY_IMAGE_CODE);
        String codeInRequest = request.getParameter("imageCode");

        if (StringUtils.isEmpty(codeInRequest))
            throw new ValidateCodeException("验证码不能为空");

        if (null == codeInSession)
            throw new ValidateCodeException("验证码不存在");

        if (codeInSession.isExpire()) {
            request.getSession().removeAttribute(ValidateController.SESSION_KEY_IMAGE_CODE);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!codeInRequest.equals(codeInSession.getCode()))
            throw new ValidateCodeException("验证码不正确");

        request.getSession().removeAttribute(ValidateController.SESSION_KEY_IMAGE_CODE);

    }

}
