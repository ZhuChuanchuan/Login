package com.zhcc.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by ZHCC on 2018/9/17
 */
@WebServlet(name = "AsyncServlet",urlPatterns = {"/AsyncServlet"},asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=GBK");
        PrintWriter out=response.getWriter();

        out.println("进入servlet的时间：" + new java.util.Date() + " </br>");
        //异步调用
        AsyncContext actx=request.startAsync();
        actx.setTimeout(60 * 1000);
        actx.start(new GetBookTarget(actx));
    }

    private class GetBookTarget implements Runnable {
        private AsyncContext actx=null;
        public GetBookTarget(AsyncContext actx) {
            this.actx=actx;
        }

        @Override
        public void run() {

            //等待3秒，模拟业务时长
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ServletRequest request=actx.getRequest();

            request.setAttribute("book", "i am a book");
            actx.dispatch("/async.jsp");

        }
    }
}
