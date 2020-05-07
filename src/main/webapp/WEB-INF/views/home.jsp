<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

<!-- methodでどのように, actionでどこにデータを送信するかを定義-->
	<form method="get" action="<%=request.getContextPath()%>/skillUpload">
		<button >skill-upload</button>
	</form>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>


