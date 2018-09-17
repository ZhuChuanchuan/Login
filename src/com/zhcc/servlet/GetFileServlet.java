package com.zhcc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Create by ZHCC on 2018/9/17
 */
@WebServlet(name = "GetFileServlet")
@MultipartConfig //处理文件
public class GetFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part part = request.getPart("xx"); //直接getPart获取
        part.getContentType();//文件类型
        part.getSize();//文件大小
        String fileNameInfo=part.getHeader("content-disposition");//包含原始字符串文件名信息
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
