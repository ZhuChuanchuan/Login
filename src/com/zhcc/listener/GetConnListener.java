package com.zhcc.listener; /**
 * Create by ZHCC on 2018/9/17
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener()
public class GetConnListener implements ServletContextListener{

    // Public constructor is required by servlet spec
    public GetConnListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

        ServletContext application=sce.getServletContext();
        String driver = application.getInitParameter("driver");
        String url = application.getInitParameter("url");
        String user = application.getInitParameter("username");
        String pass = application.getInitParameter("password");

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            application.setAttribute("conn",conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
      ServletContext application=sce.getServletContext();
        Connection conn = (Connection) application.getAttribute("conn");
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
