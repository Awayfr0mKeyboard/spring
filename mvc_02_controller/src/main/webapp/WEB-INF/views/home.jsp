<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> --%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! - ${sessionScope.a}
</h1>
<a href="doA">doA</a> <br/>
<form action="doA" method="post">
	<button>doA Post</button>
</form>
<br/>
<a href="doB">doB</a> <br/>
<a href="doC">doC</a> <br/>

<a href="doD?msg=doDGetRequest">doD</a> <br/> <br/>

<form action="doD" method="post">
	<input type="text" name="name" required /> <br/>
	<input type="number" name="price" required /> <br/>
	<input type="submit" value="전송" />
</form>

<hr/>

<form action="doH" method="post">
	<input type="text" name="name" required /> <br/>
	<input type="number" name="price" required /> <br/>
	<input type="submit" value="전송" />
</form>

</body>
</html>
