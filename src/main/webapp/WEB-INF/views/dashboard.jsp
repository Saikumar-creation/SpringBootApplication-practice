<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>Dashboard</title>
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/resources/favicon.png?v=1">


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
.btn-metallic {
	background: linear-gradient(135deg, #6c757d, #495057);
	border: none;
	color: #fff;
	border-radius: 12px;
	padding: 8px 16px;
}

.btn-metallic:hover {
	background: linear-gradient(135deg, #495057, #343a40);
}

.btn-metallic i {
	color: #fff;
}

.btn-metallic:hover i {
	color: #ffd700; /* gold on hover */
}
</style>
<style>
body {
	background: linear-gradient(135deg, #2c2c2c, #4f4f4f);
	color: #fff;
	font-family: "Segoe UI", sans-serif;
}

.bg-dark {
	background-color: rgb(55, 102, 150) !important;
}

.card {
	border-radius: 20px;
	background: linear-gradient(145deg, #3a3a3a, #2e2e2e);
	box-shadow: 8px 8px 16px #1a1a1a, -8px -8px 16px #4a4a4a;
	color: #fff;
}

.table thead {
	background-color: #5c5c5c;
	color: #fff;
}
</style>
</head>
<body>
	<div class="container mt-4">
		<div style="display: flex; justify-content: space-around">
			<h2 class="text-center mb-4">Welcome To Employee Dashboard</h2>
			<a style="height: 40px" role="button" href="/sba/logout"
				class="btn btn-metallic">Logout</a>

		</div>
		<p>This is your custom dashboard after login.</p>
		<!-- Summary Cards -->
		<div class="row mb-4">
			<c:if test="${not empty errors}">
				<div class="alert alert-danger">${errors}</div>
			</c:if>

			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger">${errorMessage}</div>
			</c:if>
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success">${successMessage}</div>
			</c:if>



			<div class="col-md-4">
				<div class="card p-3 text-center">
					<h4>Total Employees</h4>
					<h2>${objDashboardDto.totalEmployees}</h2>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card p-3 text-center">
					<h4>Active Employees</h4>
					<h2>${objDashboardDto.totalActiveEmployees}</h2>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card p-3 text-center">
					<h4>InActive Employees</h4>
					<h2>${objDashboardDto.totalInactiveEmployees}</h2>
				</div>
			</div>
		</div>

		<!--Active Employee List -->
		<div class="card p-3">
			<h4>Activee Employee Details</h4>
			<table class="table table-dark table-hover mt-3">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Department</th>
						<th>Email</th>
						<th>Mobile</th>
						<th>Age</th>
						<th>Status</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(objDashboardDto.employeeDto) > 0}">
							<c:forEach var="emp" items="${objDashboardDto.employeeDto}">
								<c:if test="${emp.status eq 'Active'}">
									<tr>
										<td>${emp.id}</td>
										<td>${emp.name}</td>
										<td>${emp.dept}</td>
										<td>${emp.email}</td>
										<td>${emp.mobile}</td>
										<td>${emp.age}</td>
										<td><span
											class="badge bg-${emp.status eq 'Active' ? 'success' : 'danger'}">
												${emp.status} </span></td>
										<td>
											<!-- Edit button --> <a type="button"
											class="btn btn-metallic btn-sm"
											onclick="getEmployee(${emp.id})" title="Edit"> <i
												class="fas fa-pencil-alt"></i>
										</a> <!-- Delete button --> <a type="button"
											class="btn btn-danger btn-sm"
											onclick="confirmDelete(${emp.id})" title="Delete"> <i
												class="fas fa-trash-alt"></i>
										</a>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td style="text-align: center;" colspan="7">No Employee
									Details Found</td>
							</tr>

						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<button type="button" class="btn btn-metallic" data-bs-toggle="modal"
				style="width: max-content" data-bs-target="#addEmployeeModal">+
				Add Employee</button>
		</div>
		<!--InActive Employee List -->
		<div class="card p-3">
			<h4>InActive Employee Details</h4>
			<table class="table table-dark table-hover mt-3">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Department</th>
						<th>Email</th>
						<th>Mobile</th>
						<th>Age</th>
						<th>Status</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(objDashboardDto.employeeDto) > 0}">
							<c:forEach var="emp" items="${objDashboardDto.employeeDto}">
								<c:if test="${emp.status eq 'InActive'}">
									<tr>
										<td>${emp.id}</td>
										<td>${emp.name}</td>
										<td>${emp.dept}</td>
										<td>${emp.email}</td>
										<td>${emp.mobile}</td>
										<td>${emp.age}</td>
										<td><span
											class="badge bg-${emp.status eq 'Active' ? 'success' : 'danger'}">
												${emp.status} </span></td>
										<td>
											<!-- Edit button --> <a type="button"
											class="btn btn-metallic btn-sm"
											onclick="getEmployee(${emp.id})" title="Edit"> <i
												class="fas fa-pencil-alt"></i>
										</a> <!-- Delete button --> <a type="button"
											class="btn btn-danger btn-sm"
											onclick="confirmDelete(${emp.id})" title="Delete"> <i
												class="fas fa-trash-alt"></i>
										</a>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td style="text-align: center;" colspan="7">No Employee
									Details Found</td>
							</tr>

						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<button type="button" class="btn btn-metallic" data-bs-toggle="modal"
				style="width: max-content" data-bs-target="#addEmployeeModal">+
				Add Employee</button>
		</div>
		<!--  Add Employee screen-->
		<div class="modal fade" id="addEmployeeModal" tabindex="-1"
			aria-labelledby="addEmployeeModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content bg-dark text-white">
					<div class="modal-header">
						<h5 class="modal-title" id="addEmployeeModalLabel">Add New
							Employee</h5>
						<button type="button" class="btn-close btn-close-white"
							data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="${pageContext.request.contextPath}/saveEmployee"
						method="post">
						<div class="modal-body">
							<div class="mb-3">
								<label for="name" class="form-label">Name</label> <input
									type="text" class="form-control" id="name" name="name" required>
								<input type="hidden" class="form-control" id="id" name="id">
							</div>
							<div class="mb-3">
								<label for="dept" class="form-label">Department</label> <input
									type="text" class="form-control" id="dept" name="dept" required>
							</div>
							<div class="mb-3">
								<label for="email" class="form-label">Email</label> <input
									type="email" class="form-control" id="email" name="email"
									required>
							</div>
							<div class="mb-3">
								<label for="mobile" class="form-label">Mobile</label> <input
									type="text" class="form-control" id="mobile" name="mobile"
									required>
							</div>
							<div class="mb-3">
								<label for="age" class="form-label">Age</label> <input
									type="number" class="form-control" id="age" name="age" min="18"
									required>
							</div>
							<div class="mb-3">
								<label for="status" class="form-label">Status</label> <select
									class="form-select" id="status" name="status">
									<option value="Active" selected>Active</option>
									<option value="InActive">InActive</option>
								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-metallic">Save
								Employee</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<script type="text/javascript">
function confirmDelete(empId) {
    if (confirm("Are you sure you want to delete this employee?")) {
        window.location.href = 'deleteEmployee?id=' + empId;
    }
}
function getEmployee(empId) {
    $.ajax({
        url: '/sba/employee/getEmployee/' + empId,
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            console.log(response);
            $('#name').val(response.name);
            $('#id').val(response.id);
            $('#dept').val(response.dept);
            $('#email').val(response.email);
            $('#mobile').val(response.mobile);
            $('#age').val(response.age);
            $('#status').val(response.status);
            $('#addEmployeeModal').modal('show');
        },
        error: function(xhr, status, error) {
            alert("Error fetching employee: " + xhr.responseText);
        }
    });
}
</script>
</html>

