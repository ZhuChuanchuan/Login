package com.zhcc.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Create by ZHCC on 2018/9/17
 */
@WebFilter(filterName = "LogFilter")  //拦截所有请求
public class LogFilter implements Filter {

    private FilterConfig config;
    public void destroy() {
        this.config=null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //请求预处理
        ServletContext context=this.config.getServletContext();
        long before=System.currentTimeMillis();
        System.out.println("开始过滤...");
        HttpServletRequest hrequest=(HttpServletRequest)req;
        //输出提示信息
        System.out.println("Filter已拦截到请求地址：" + hrequest.getServletPath());

        //请求依然放行
        chain.doFilter(req,resp);

        //服务器响应后处理
        long after=System.currentTimeMillis();
        System.out.println("过滤结束");
        System.out.println("请求被定位到"+hrequest.getRequestURI()+" 所花时间"+(after-before));
    }

    public void init(FilterConfig config) throws ServletException {
        this.config=config;
    }

}
