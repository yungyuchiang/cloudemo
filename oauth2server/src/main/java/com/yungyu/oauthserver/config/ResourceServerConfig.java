package com.yungyu.oauthserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
@Order(2) // 资源服务器要在认证服务器后面启动，保证先认证
public class ResourceServerConfig {
}
