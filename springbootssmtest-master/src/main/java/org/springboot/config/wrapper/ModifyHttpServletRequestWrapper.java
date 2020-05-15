package org.springboot.config.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @ClassName ModifyHttpServletRequestWrapper
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 9:43
 * @Version 1.0
 */
public class ModifyHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String> customHeaders = new HashMap();

    public ModifyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public void putHeader(String name, String value) {
        this.customHeaders.put(name, value);
    }

    public String getHeader(String name) {
        String value = (String)this.customHeaders.get(name);
        return value != null ? value : ((HttpServletRequest)this.getRequest()).getHeader(name);
    }

    public Enumeration<String> getHeaderNames() {
        Set<String> set = new HashSet(this.customHeaders.keySet());
        Enumeration enumeration = ((HttpServletRequest)this.getRequest()).getHeaderNames();

        while(enumeration.hasMoreElements()) {
            String name = (String)enumeration.nextElement();
            set.add(name);
        }

        return Collections.enumeration(set);
    }
}