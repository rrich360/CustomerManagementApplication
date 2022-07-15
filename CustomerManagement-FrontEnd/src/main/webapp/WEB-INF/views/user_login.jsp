<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<style>
 html, 
 body {
  background-color:#fff;
  -webkit-font-smoothing: antialiased;
  font: normal 14px Roboto,arial,sans-serif;
}

.container {
    padding: 25px;
    position: fixed;
}

.form-login {
    background-color: #EDEDED;
    padding-top: 10px;
    padding-bottom: 20px;
    padding-left: 20px;
    padding-right: 20px;
    border-radius: 15px;
    border-color:#d2d2d2;
    border-width: 5px;
    box-shadow:0 1px 0 #cfcfcf;
}

h4 { 
 border:0 solid #fff; 
 border-bottom-width:1px;
 padding-bottom:10px;
 text-align: center;
}

.form-control {
    border-radius: 10px;
}

.wrapper {
    text-align: center;
}
 </style>
 
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
	<div class="row">
		<div class="col-md-offset-5 col-md-3">
			<div class="form-login">
			<br></br>
				<h4>Welcome back. Please enter your Administrator credentials : </h4>
			<br></br>
			
				<form:form modelAttribute="Login" method="post" class="form-horizontal">

					<form:input path="username" type="text" class="form-control input-sm chat-input" placeholder="username or email" id="username"/>
					<br></br>
					
					<form:errors path = "username" cssClass="text-danger"/>
					
					<form:input path="password" type="password" class="form-control input-sm chat-input" placeholder="password" id="password"/>
					<br></br>
					
					<div class="wrapper">
						<form:button type='success' class="btn btn-success" id="loginButton">Login</form:button>
						<div style="color: red" id="errorMessage">${error}</div>
					</div>
					
				</form:form>
			</div>

		</div>
	</div>
</div>
</body>
</html>