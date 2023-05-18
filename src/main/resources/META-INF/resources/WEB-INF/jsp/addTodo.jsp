<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Add Todos</title>
	</head>
	<body>
        <%@ include file="common/navigation.jspf" %>

		<div class="container">
			<h1>Enter Todo details:</h1>
			<form:form method="post" modelAttribute="todo">
				 Description:<form:input type="text" path="description" required="required"/>
				 <form:errors path="description"/>
				 target date:<form:input type="text" path="targetDate" required="required"/>
				 <form:errors path="targetDate"/>
                 <form:input type="hidden" path="id"/>
                 <form:input type="hidden" path="done"/>
				<input type="submit">
			</form:form>
		</div>
	</body>
</html>