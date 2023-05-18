<html>
	<head>
		<title>Welcome Page</title>
	</head>
	<body>
	    <%@ include file="common/navigation.jspf" %>

		<div class="container">
			<h1>Welcome ${name}! Have a nice day. </h1>
			<a href="list-todos">Manage</a> your todos
		</div>
	</body>
</html>