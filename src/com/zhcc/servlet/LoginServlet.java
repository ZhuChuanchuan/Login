package com.zhcc.servlet;

import com.zhcc.dao.DbDao;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Create by ZHCC on 2018/9/14
 */
@WebServlet(name = "LoginServlet",urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errMsg="";
        //Servlet本身并不输出响应到客户端，因此必须将请求转发到师徒页面
        RequestDispatcher rd;

        //获取请求参数
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");

        try {
            String driver = getServletContext().getInitParameter("driver");
            String url = getServletContext().getInitParameter("url");
            String dbUsername = getServletContext().getInitParameter("username");
            String dbPassword = getServletContext().getInitParameter("password");

            DbDao dao = new DbDao(driver, url, dbUsername, dbPassword);
            ResultSet rs = dao.query("select PASSWORD from TB_USER where USERNAME=?",username);
            if (rs.next()) {
                String s=rs.getString("password");
                if (rs.getString("password").equals(pass)) {
                    //获取session对象
                    HttpSession session = request.getSession(true);
                    //设置session属性，跟踪用户会话状态
                    session.setAttribute("name", username);
                    //获取转发对象
                    rd = request.getRequestDispatcher("/welcome.jsp");
                    //转发请求
                    rd.forward(request, response);
                } else {
                    errMsg += "您的用户名密码不符合，请重新输入";
                }
            } else {
                errMsg += "您的用户名不存在，请先注册";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (errMsg != null && !errMsg.equals("")) {
            rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("err", errMsg);
            rd.forward(request,response);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("初始化");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
