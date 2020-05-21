package org.springboot.config.filter;

import org.springboot.config.jwt.filter.JwtTokenFilter;
import org.springboot.config.properties.BhomeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @ClassName ConfigurationFilter
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/15 15:32
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(
        prefix = "mcx"
)
public class ConfigurationFilter {
    public ConfigurationFilter() {
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public FilterRegistrationBean gwtFilter(Filter jwtTokenFilter, BhomeProperties bhomeProperties) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(jwtTokenFilter);
        registration.addUrlPatterns(bhomeProperties.getUrlPatterns());
        registration.setName("gwtFilter");
        registration.setOrder(0);
        return registration;
    }
}
