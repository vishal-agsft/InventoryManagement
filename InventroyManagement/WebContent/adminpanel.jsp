<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page session="false"%>
<%
HttpSession httpSession =request.getSession();
if(httpSession==null){
	response.sendRedirect("login.jsp");
}


// Create cookies for first and last names.      
Cookie mail = new Cookie("mail",
			  request.getParameter("email"));
Cookie pass = new Cookie("pass",
			  request.getParameter("pwd"));

// Set expiry date after 24 Hrs for both the cookies.
mail.setMaxAge(60*60*24); 
pass.setMaxAge(60*60*24); 

// Add both the cookies in the response header.
response.addCookie( mail );
response.addCookie( pass );
%>


<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#add {
	margin-top: -5%;
}

.divider {
	width: 5px;
	height: auto;
	display: inline-block;
}

dialog {
	width: 500px;
	background: #e8e8e8;
	border: 1px solid #dadada;
	font-family: sans-serif;
	padding: 5px 10px 20px 20px;
}

th, td {
	padding: 20px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

html {
	background: url(images/bg.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

tr:hover {
	background-color: #f5f5f5
}
#Loaderdiv{
display: none;

}
#loader {
display:none;
  position: absolute;
  left: 50%;
  top: 50%;
  z-index: 1;
  width: 150px;
  height: 150px;
  margin: -75px 0 0 -75px;
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite;
  animation: spin 2s linear infinite;
}

@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

/* Add animation to "page content" */
.animate-bottom {
  position: relative;
  -webkit-animation-name: animatebottom;
  -webkit-animation-duration: 1s;
  animation-name: animatebottom;
  animation-duration: 1s
}

@-webkit-keyframes animatebottom {
  from { bottom:-100px; opacity:0 } 
  to { bottom:0px; opacity:1 }
}

@keyframes animatebottom { 
  from{ bottom:-100px; opacity:0 } 
  to{ bottom:0; opacity:1 }
}

#myDiv {
  display: none;
  text-align: center;
}
</style>
<script type="text/javascript">
	function Myconfirm(itemid, itemName, emailid, requestid) {
		$("#loader").fadeIn("slow");
		var i = itemid;
		var j = itemName;
		var k = emailid;
		var l = requestid;
		$.ajax({
			type : "POST",
			url : "approvereq",
			data : {
				itemid : i,
				itemName : j,
				emailid : k,
				requestid : l
			},
			success : function(result) {
				console.log(result);
				$("#loader").fadeOut();
				/* alert("Request Approved!!") */
				$("#ApproveDecline").modal('show');
				//document.location.href = 'agilelogin';
				/* var successUrl = "adminpanel.jsp";
				window.location.href = successUrl; */
			}
		});
	}
	function Mydecline(itemid, itemName, emailid, requestid) {
		$("#loader").fadeIn("slow");
		var i = itemid;
		var j = itemName;
		var k = emailid;
		var l = requestid;
		$.ajax({
			type : "POST",
			url : "declinereq",
			data : {
				itemid : i,
				itemName : j,
				emailid : k,
				requestid : l
			},
			success : function(result) {
				$("#loader").fadeOut();
				/* alert("Request Approved!!") */
				$("#myDecline").modal('show');
				/* var successUrl = "agilelogin";
				window.location.href = successUrl; */
			}
		});
	}
	
	$('#usermodaladd').on('shown.bs.modal', function() {
	    $(document).off('focusin.modal');
	});
</script>
<title>Admin Panel</title>
</head>
<body>

	<jsp:include page="Topview.jsp" />
	<jsp:include page="logout.jsp" />
	<!-- <div class="col-lg-12 loader" id="Loaderdiv">
	<img src="/images/Pure-CSS-loading-spiner.jpg"></img>
	
	</div> -->
	<div class="container col-lg-12 col-lg-offset-4" id="MainDiv">
	<div id="loader"></div>

<div style="display:none;" id="myDiv" class="animate-bottom">
 
</div>
		<h3>Inventory Management</h3>
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#inventory">Inventory</a></li>
			<li><a data-toggle="tab" href="#users">Users</a></li>
			<li><a data-toggle="tab" href="#request">Requests</a></li>
		</ul>
		<div class="tab-content col-lg-8 ">
			<div id="inventory" class="tab-pane fade in active">

				<div class="col-lg-12">
					<!-- <input type="button" class="btn btn-primary"
							value="View Inventory" id="viewinventory"> -->
					<div class="container">
						<h2>Inventory List</h2>
						<div class="col-lg-12">
							<div class="col-lg-10">
								<table class="table">
									<thead class="thead-inverse">
										<tr>
											<th>Items</th>
											<th>Quantity</th>
											<th>Description</th>
											<th>Edit</th>
											<!-- <th>Edit Items</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${itemsList}" var="user">
											<tr>

												<td>${user.itemName}</td>
												<td>${user.quantity}</td>
												<td>${user.description}</td>
												<td>
													<button data-toggle="modal" type="button"
														class="btn btn-primary btn-xs"
														onclick="setvals('${user.itemName}','${user.quantity}','${user.description}')"
														data-title="Edit">Edit</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="col-lg-2" id="add">
								<button type="button" class="btn btn-info" data-toggle="modal"
									data-target="#myModal" id="addinventory">Add Items</button>
							</div>

						</div>
					</div>

					<!--Add item Modal -->
					<form action="newitem" method="post">
						<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
							aria-labelledby="myModal">
							<div class="modal-dialog modal-md">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title">Add Items</h4>
									</div>
									<div class="modal-body col-lg-12 col-sm-12 col-xs-12">

										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Item Name </label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="text" class="form-control" style="float: right;"
												class="pull-left clear-float-mobile" name="itemname" />
										</div>
										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Quantity</label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="text" class="form-control" style="float: right;"
												class="pull-left clear-float-mobile" name="quantity" />
										</div>

										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Description: </label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="text" class="form-control" style="float: right;"
												class="pull-left clear-float-mobile" name="discription" />
										</div>

									</div>
									<div class="modal-footer clear_both">
										<button type="submit" class="btn btn-primary pull-left"
											style="float: right;">Add</button>
										<!-- <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  -->
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">Cancel</button>
									</div>
								</div>
							</div>
						</div>
					</form>

					<!-- Edit item Modal -->
					<form action="edititem" method="post">
						<div id="myModal2" class="modal fade" tabindex="-1" role="dialog"
							aria-labelledby="editModal">
							<div class="modal-dialog modal-md">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModal2">Update Items</h4>
									</div>
									<div class="modal-body col-lg-12 col-sm-12 col-xs-12">

										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Item Name </label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="text" class="form-control" style="float: right;"
												class="pull-left clear-float-mobile" name="itemName"
												id="itemName" value="" placeholder="" required="required" />
										</div>
										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Quantity</label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="text" class="form-control" style="float: right;"
												class="pull-left clear-float-mobile" name="itemQuantities"
												id="itemQuantities" value="" placeholder=""
												required="required" />
										</div>

										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Description: </label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="text" class="form-control" style="float: right;"
												class="pull-left clear-float-mobile" name="itemDescription"
												id="itemDescription" value="" placeholder=""
												required="required" />
										</div>
									</div>
									<div class="modal-footer clear_both">
										<button type="submit" class="btn btn-primary pull-left"
											style="float: right;">Update</button>
										<!-- <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  -->
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">Cancel</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- Users Tab view -->
			<div id="users" class="tab-pane fade">

				<div class="col-lg-12">
					<div class="container">
						<h2>Users List</h2>
						<div class="col-lg-12">
							<div class="col-lg-10">
								<table class="table">
									<thead class="thead-inverse">
										<tr>
											<th>FirstName</th>
											<th>LastName</th>
											<th>Email</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${usersList}" var="user">
											<tr>
												<td>${user.firstName}</td>
												<td>${user.lastName}</td>
												<td>${user.emailId}</td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="col-lg-2" id="add">
								<button type="button" class="btn btn-info" data-toggle="modal"
									data-target="#usermodaladd" id="addUser">Add Users</button>
							</div>
						</div>
					</div>

					<!--Add user Modal -->
						<form action="insertuser" method="post">
						<div id="usermodaladd" class="modal fade" tabindex="-1"
							role="dialog">
							<div class="modal-dialog modal-md">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title">Add Users</h4>
									</div>
									<div class="modal-body col-lg-12 col-sm-12 col-xs-12">
									
										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">First Name </label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="text" class="form-control" style="float: right;"
												class="pull-left clear-float-mobile" name="firstName"
												required="required" />
										</div>
										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Last Name</label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="text" class="form-control" style="float: right;"
												class="pull-left clear-float-mobile" name="lastName"
												required="required" />
										</div>

										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Email </label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="email" class="form-control"
												style="float: right;" class="pull-left clear-float-mobile"
												name="emailid" required="required" />
										</div>

										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Password </label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="password" class="form-control"
												style="float: right;" class="pull-left clear-float-mobile"
												name="password" required="required" />
										</div>
										<div class="col-md-4 col-sm-4 col-xs-12 form-group">
											<label
												style="font-size: 18px; font-weight: 300; float: right;"
												class="pull-right clear-float-mobile">Is Admin </label>
										</div>
										<div class="col-md-8 col-sm-8 col-xs-12 form-group">
											<input type="checkbox"
												style="float: left;" class="pull-left clear-float-mobile"
												name="admincheck" value="chk" />
										</div>
										
									</div>
									<div class="modal-footer clear_both">
										<button type="submit" class="btn btn-primary pull-left"
											style="float: right;">Add</button>
										<!-- <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  -->
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">Cancel</button>
									</div>
									
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>

			<!-- Request Tab view -->
			<div id="request" class="tab-pane fade">
				<!-- <h3>Request Panel</h3> -->
				<div class="col-lg-12">
					<!-- <input type="button" class="btn btn-primary"
							value="View Inventory" id="viewinventory"> -->
					<div class="container">
						<h2>Request List</h2>
						<div class="col-lg-12">
							<div class="col-lg-12">
								<table class="table">
									<thead class="thead-inverse">
										<tr>
											<th>FirstName</th>
											<th>LastName</th>
											<th>Email</th>
											<th>Requested item</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${reqList}" var="user">
											<tr>
												<td>${user.firstName}</td>
												<td>${user.lastName}</td>
												<td>${user.emailId}</td>
												<td>${user.itemName}</td>
												<td>
													<button id="approve" class="btn btn-success btn-xs"
														onclick="Myconfirm('${user.itemId}','${user.itemName}','${user.emailId}','${user.requestId}')"
														value="approve" name="requestbtn">Approve</button>
													<div class="divider"></div>
													<div id="approvediv" style="display: none;">
														<p></p>
													</div>
													<button id="decline" type="button"
														class="btn btn-danger btn-xs"
														onclick="Mydecline('${user.itemId}','${user.itemName}','${user.emailId}','${user.requestId}')"
														value="decline" name="requestbtn">Decline</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#ApproveDecline">Open Modal</button> -->

<!-- Modal -->
<form action="agilelogin" method="post">
<div id="ApproveDecline" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Request status</h4>
      </div>
      <div class="modal-body">
        <p id="approvep">Request approved Say ok.</p>
         <!-- <p id="declinep">Request declined Say ok.</p> -->
      </div>
      <div class="modal-footer">
      <button type="submit" class="btn btn-info">OK</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
</form>
<!-- Modal -->
<form action="agilelogin" method="post">
<div id="myDecline" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Request status</h4>
      </div>
      <div class="modal-body">
        
         <p id="declinep">Request declined Say ok.</p>
      </div>
      <div class="modal-footer">
      <button type="submit" class="btn btn-info">OK</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
<script type="text/javascript">
	function setvals(itemName, itemQuantities, itemDescription) {
		$("#itemName").val(itemName);
		$("#itemQuantities").val(itemQuantities);
		$("#itemDescription").val(itemDescription);
		$('#myModal2').modal('show');
	}
</script>
</html>