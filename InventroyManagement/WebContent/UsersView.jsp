<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@page language="java" import="com.agile.bl.model.*"%>
<%@ page isELIgnored="false"%>
<%@ page session="false"%>
<!DOCTYPE html>
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
<link rel="stylesheet" href="css/users.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users Panel</title>
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
			HttpSession httpSession = request.getSession(false);
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
			 //response.setIntHeader("Refresh", 5);
				Calendar calendar = new GregorianCalendar();
				String am_pm;
				int hour = calendar.get(Calendar.HOUR);
				int minute = calendar.get(Calendar.MINUTE);
				int second = calendar.get(Calendar.SECOND);
				if (calendar.get(Calendar.AM_PM) == 0)
					am_pm = "AM";
				else
					am_pm = "PM";
				String CT = hour + ":" + minute + ":" + second + " " + am_pm;
		%>
		<div class="container col-lg-12 pull-left col-offset-8">
			<div class="container col-lg-4 ">
				<div class="row login_box">
					<div class="col-md-12 col-xs-12" align="center">
						<div class="line">
							<h3><%=CT %></h3>
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

				<%-- <div class="col-lg-4">
					<h3>Select items</h3>
					<div class="dropdown" id="itemdropdownlist">
						<button class="dropbtn" id="dropitem">Inventories</button>
						<div class="dropdown-content">
							<ul class="dropdown">

								<%
									List<AgileItems> itemlist = (List<AgileItems>) request.getAttribute("itemsList");
									Iterator<AgileItems> itr2 = itemlist.iterator();
									AgileItems obj = null;
									while (itr2.hasNext()) {
										obj = itr2.next();
								%>



								<%
									while (itr2.hasNext()) {
											obj = itr2.next();
								%>
								<li><a
									href="javascript:itemSelected('<%=obj.getItemName()%>')"><i
										class="icon-twitter icon-large"></i><%=obj.getItemName()%></a></li>

							</ul>


							<%
								}
								}
							%>

						</div>
					</div>
				</div> --%>
				<div class="col-lg-4">
					<h3>Select Inventory</h3>

					<div class="form-group">
						<div class="btn-group bootstrap-select"
							style="width: 100%;">
							<button type="button" class="btn dropdown-toggle btn-default form-control"
								data-toggle="dropdown" role="button" title="Inventory"
								 id="quantdrop" style="width: 100%;">
								<span class="filter-option pull-left">Items</span>&nbsp;<span
									class="bs-caret"><span class="caret"></span></span>
							</button>

							<div class="dropdown-menu open" role="combobox"
								style="min-width: 0px;">
								<ul class="dropdown-menu inner" role="listbox"
									aria-expanded="false">
									<%
									List<AgileItems> itemlist = (List<AgileItems>) request.getAttribute("itemsList");
										Iterator<AgileItems> itrquant = itemlist.iterator();
										AgileItems quantobj = null;
										while (itrquant.hasNext()) {
											quantobj = itrquant.next();
									%>

									<li data-original-index="0" class="selected"><a
										tabindex="0" class="" data-tokens="null" role="option"
										aria-disabled="false"
										href="javascript:itemSelected('<%=quantobj.getItemName()%>')"><span
											class="text"><%=quantobj.getItemName() %></span><span class=""></span></a></li>
									<!-- <li data-original-index="1"><a tabindex="0" class=""
										data-tokens="null" role="option" aria-disabled="false"
										aria-selected="false"><span class="text">Ketchup</span><span
											class="glyphicon glyphicon-ok check-mark"></span></a></li>
									<li data-original-index="2"><a tabindex="0" class=""
										data-tokens="null" role="option" aria-disabled="false"
										aria-selected="false"><span class="text">Relish</span><span
											class="glyphicon glyphicon-ok check-mark"></span></a></li>
									<li data-original-index="3"><a tabindex="0" class=""
										data-tokens="null" role="option" aria-disabled="false"
										aria-selected="false"><span class="text">All of the
												above (and much, much more!)</span><span
											class="glyphicon glyphicon-ok check-mark"></span></a></li> -->
											
									<%} %>
								</ul>
								
							</div>

						</div>
						
						</div>
				</div>
				<div class="col-lg-3" id="quantitydiv">
					
					<h3>Quantity</h3>
					<div class="col-lg-4" id="display" style="display:none;"></div>
					
					<div class="col-lg-8" id="textdiv" style="display:none;">
					<input type="text" class="form-control" name="quantitytext" required="required" id="quantreq"/>
					</div>
					</div>
				<div class="col-lg-4" style="display: none" id="descriptiondiv">
				
				</div>
				<div class="col-lg-1" style="display: none" id="requestbtn">
				
				
				
</div>


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
        <p>Item Requested</p>
        
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
 <script type="text/javascript" src="js/users.js"></script>
</html>