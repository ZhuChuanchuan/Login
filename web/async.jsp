<%--
  Created by IntelliJ IDEA.
  User: ZHCC
  Date: 2018/9/17
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    if (request.getAttribute("book") != null) {

        out.print(request.getAttribute("book")+"<br/>");
    }

    out.println("异步结束"+new java.util.Date());
    if (request.isAsyncStarted()) {
        //完成异步调用
        request.getAsyncContext().complete();
    }
%>
</body>
</html>
