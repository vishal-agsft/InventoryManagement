<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@page language="java" import="com.agile.bl.model.*"%>
<%@ page isELIgnored="false"%>
<%@ page session="false"%>



<%
HttpSession httpSession =request.getSession();
if(httpSession==null){
	response.sendRedirect("login.jsp");
}


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Raleway:400,200'
	rel='stylesheet' type='text/css'>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#dropDiv {
	margin-top: 4%;
	margin-left: -7%;
	border-style: ridge;
	border-color: #0000;
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
	/* Method Ajax call for Making New request by user as per item  */
	function makeRequest(itemname) {
		$("#loader").fadeIn();
		var itemName = itemname;

		$.ajax({
			type : "POST",
			url : "newrequest",
			data : {
				itemName : itemName
			},
			success : function(result) {
				$("#loader").fadeOut();
				$("#ApproveDecline").modal('show');
				/* console.log(result) */
				/* alert("requested")
				var successUrl = "agilelogin";
				window.location.href = successUrl;  */
			}
		});

	}
</script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Topview.jsp" />
	<jsp:include page="logout.jsp" />


	<div class="col-lg-12">
	<div id="loader">
	
	</div>

<div style="display:none;" id="myDiv" class="animate-bottom">
 
</div>
		<%
		httpSession = request.getSession(false);
			String username = (String) httpSession.getAttribute("email");
			String name = null;
			String lastname = null;
			List<AgileUser> list = (List<AgileUser>) request.getAttribute("usersList");
			Iterator<AgileUser> itr = list.iterator();
			while (itr.hasNext()) {
				AgileUser au = itr.next();
				if (username.equals(au.getEmailId())) {
					name = au.getFirstName();
					lastname = au.getLastName();
				}
			}
		%>
		<div class="container col-lg-12 pull-left col-offset-8">
			<div class="container col-lg-6 ">
				<div class="row login_box">
					<div class="col-md-12 col-xs-12" align="center">
						<div class="line">
							<h3>12 : 30 AM</h3>
						</div>
						<div class="outter">
							<img src="http://lorempixel.com/output/people-q-c-100-100-1.jpg"
								class="image-circle" />
						</div>
						<h1><%=name%></h1>
						<!-- <span>AGILE</span> -->
					</div>
					<div class="col-md-6 col-xs-6 follow line" align="center">
						<h3>
							Profile<br /> <span><%=name%> <%=lastname%></span>
						</h3>
					</div>
					<div class="col-md-6 col-xs-6 follow line" align="center">
						<h3>
							Email <br /> <span><%=username%></span>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="container col-lg-12 " id="dropDiv">
			<div class="col-lg-12">
				<h3>You may Request here</h3>
				<table class="table">
					<thead class="thead-inverse">
						<tr>
							<th>Items</th>
							<th>Quantity</th>
							<th>Description</th>
							<th>Request</th>
							<!-- <th>Edit Items</th> -->
						</tr>
					</thead>
					<tbody>
						<%
							List<AgileItems> itemlist = (List<AgileItems>) request.getAttribute("itemsList");
							Iterator<AgileItems> itr2 = itemlist.iterator();
							AgileItems obj = null;
							while (itr2.hasNext()) {
								obj = itr2.next();
						%>
						<tr>
							<%
								String iname = obj.getItemName();
							%>
							<td><%=obj.getItemName()%></td>
							<td><%=obj.getQuantity()%></td>
							<td><%=obj.getDescription()%></td>
							<td>
								<button type="button" class="btn btn-success btn-xs"
									onclick="makeRequest('<%=iname%>')">Request Now</button>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<%-- 
				<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="#"><%=obj.getItemName()%></a></li>
				</ul> --%>
			</div>

		</div>
		<!-- Modal -->
<form action="agilelogin" method="post">
<div id="ApproveDecline" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Request</h4>
      </div>
      <div class="modal-body">
        <p>item Requested Say ok.</p>
        
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
</html>