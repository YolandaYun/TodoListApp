<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Login Page</title>
	</head>
	<body>
        <%@ include file="common/navigation.jspf" %>

		<div>
		    <h2>Nice to see you, ${name}</h2>
		</div>

		<div>
            <h1>Your Todos</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Description</th>
                        <th>Target Date</th>
                        <th>Is Done?</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.id}</td>
                            <td>${todo.description}</td>
                            <td>${todo.targetDate}</td>
                            <td>${todo.done}</td>
                            <td> <a href="delete-todo?id=${todo.id}">Delete</a>   </td>
                            <td> <a href="update-todo?id=${todo.id}">Update</a>   </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <a href="add-todo">Add Todo</a>
	</body>
</html>