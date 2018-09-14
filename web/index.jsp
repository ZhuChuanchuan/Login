<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
<form id="login" method="post" action="LoginServlet">
  用户名<input type="text" name="username"/><br/>
  密码:<input type="password" name="pass"/><br/>
  <input type="submit" value="登录"/><br/>
</form>
  <span style="color:red;font-weight: bold">
    <%
      if (request.getAttribute("err") != null) {
          out.print(request.getAttribute("err")+"<br/>");
      }
    %>
  </span>
  </body>
</html>