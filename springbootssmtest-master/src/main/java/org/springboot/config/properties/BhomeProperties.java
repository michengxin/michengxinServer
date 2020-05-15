package org.springboot.config.properties;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

/**
 * @ClassName BhomeProperties
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/9 17:53
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(
        prefix = "bhome"
)
public class BhomeProperties {

    private String[] excludedUrl;

    private String[] urlPatterns = new String[]{"/*"};
    private Pattern excludedPattern;

    public BhomeProperties() {
    }

    public String[] getExcludedUrl() {
        return this.excludedUrl;
    }

    public void setExcludedUrl(String[] excludedUrl) {
        this.excludedUrl = excludedUrl;
        this.excludedPattern = Pattern.compile(this.getRegStr(this.excludedUrl));
    }

    public String[] getUrlPatterns() {
        return this.urlPatterns;
    }

    public void setUrlPatterns(String[] urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    public Pattern getExcludedPattern() {
        return this.excludedPattern;
    }

    private String getRegStr(String[] strArr) {
        String[] result = new String[strArr.length];

        for(int i = 0; i < strArr.length; ++i) {
            String temp = strArr[i];
            temp = temp.replace("\\", "\\\\").replace(".", "\\.").replace("*", ".*");
            temp = "^" + temp + "$";
            result[i] = temp;
        }

        return ArrayUtil.join(result, "|");
    }
}