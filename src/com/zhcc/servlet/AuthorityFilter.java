package com.zhcc.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by ZHCC on 2018/9/17
 */
@WebFilter(filterName = "AuthorityFilter",initParams = {
        @WebInitParam(name="encoding",value="GBK"),
        @WebInitParam(name="loginPage",value="/index.jsp")
})
public class AuthorityFilter implements Filter {

    private FilterConfig config;
    public void destroy() {
        this.config=null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String encoding = config.getInitParameter("encoding");
        String loginPage = config.getInitParameter("loginPage");

        req.setCharacterEncoding(encoding);
        HttpServletRequest requ=(HttpServletRequest)req;
        HttpSession session = requ.getSession(true);
        String requestPath=requ.getServletPath();
        if (session.getAttribute("user") == null && !requestPath.endsWith(loginPage)) {
            //forward到登录界面
            req.setAttribute("tip", "您还没登录");
            req.getRequestDispatcher(loginPage).forward(req,resp);
        }else{//放行请求
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {
        this.config=config;
    }

}
