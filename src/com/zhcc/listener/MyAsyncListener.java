package com.zhcc.listener; /**
 * Create by ZHCC on 2018/9/17
 */

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.IOException;

@WebListener()
public class MyAsyncListener implements AsyncListener {



    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println("-----------异步调用完成-----------");
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {

    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {

    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        System.out.println("-----------异步调用开始-----------");
    }
}
