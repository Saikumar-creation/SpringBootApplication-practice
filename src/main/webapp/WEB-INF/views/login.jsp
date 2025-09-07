<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Employee Login</title>
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/resources/favicon.png?v=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.login-container {
	background: white;
	padding: 30px 40px;
	border-radius: 10px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
	width: 100%;
	max-width: 400px;
}

.login-title {
	text-align: center;
	margin-bottom: 25px;
	font-weight: 700;
	color: #343a40;
}

.form-control:focus {
	box-shadow: none;
	border-color: #6c63ff;
}

.btn-login {
	background-color: #6c63ff;
	color: white;
}

.btn-login:hover {
	background-color: #5750d1;
}

.message {
	text-align: center;
	margin-top: 15px;
}
</style>
</head>
<body>
	<div class="login-container">
		<h2 class="login-title">Employee Login</h2>
		<form method="post" action="${pageContext.request.contextPath}/perform_login">
			<c:if test="${not empty errorMessage}">
				<p class="message text-danger">${errorMessage}</p>
			</c:if>
			<div class="mb-3">
				<label for="username" class="form-label">Username:</label> <input
					type="text" class="form-control" id="username" name="username"
					required />
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password:</label> <input
					type="password" class="form-control" id="password" name="password"
					required />
			</div>
			<div class="d-grid">
				<button type="submit" class="btn btn-login">Login</button>
			</div>
		</form>

		<c:if test="${param.error != null}">
			<p class="message text-danger">Invalid username or password.</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p class="message text-success">You have been logged out.</p>
		</c:if>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
