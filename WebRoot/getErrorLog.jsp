<%@page import="java.sql.ResultSet"%>
<%@page import="dao.DbDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看错误日志信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    错误日志信息：
    <table width="640" border="1">
    	 <%
    	DbDao dao = new DbDao("oracle.jdbc.driver.OracleDriver",
    			"jdbc:oracle:thin:@localhost:1521:oracle","system","admin");
    	//查询在线用户的记录
    	ResultSet rs = dao.query("select createDate,thread,level,class,message from SCOTT.tb_log where level = error");
    	while(rs.next()){
    %>
    <tr>
    	<td><%=rs.getString(1) %>
    	<td><%=rs.getString(2) %>
    	<td><%=rs.getString(3) %>
    	<td><%=rs.getString(4) %>
    </tr>
    <%	
    	}
     %>
    </table>
   
  </body>
</html>
