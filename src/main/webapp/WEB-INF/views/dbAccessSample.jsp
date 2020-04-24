<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prefectures</title>
</head>
<body>
	<div style="border-bottom: solid 1px #cbcbcb; padding: 10px 20px;">
		Prefectures</div>
	<form method="post" action="<%=request.getContextPath()%>/insertSample">
		<div style="padding: 5px;">
			<button type="submit">登録</button>
		</div>
	</form>
	<form method="post" action="<%=request.getContextPath()%>/updateSample">
		<div style="padding: 5px;">
			<button type="submit">更新</button>
		</div>
	</form>
	<form method="post" action="<%=request.getContextPath()%>/deleteSample">
		<div style="padding: 5px;">
			<button type="submit">削除</button>
		</div>
	</form>
​
​
	<c:if test="${!empty resultMessage}">
		<div style="color:red;font-weight: bold; ">${resultMessage}</div>
	</c:if>
	<div style="padding: 10px;">
		<table
			style="table-layout: fixed; border-collapse: collapse; width: 100%;">
			<tr>
				<th style="border: solid 1px #cbcbcb; width: 300px;">都道府県</th>
				<th style="border: solid 1px #cbcbcb;">県庁所在地</th>
				<th style="border: solid 1px #cbcbcb; width: 300px;">地方</th>
			</tr>
			<c:forEach var="item" items="${items}">
				<tr>
					<th style="border: solid 1px #cbcbcb; width: 300px;">${item.get("name")}</th>
					<th style="border: solid 1px #cbcbcb;">${item.get("capital_city")}</th>
					<th style="border: solid 1px #cbcbcb; width: 300px;">${item.get("regionName")}</th>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>