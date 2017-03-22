function requestconfirm(itemid, itemName, emailid, requestid,quant) {
	$("#loader").fadeIn("slow");
	var i = itemid;
	var j = itemName;
	var k = emailid;
	var l = requestid;
	var m=quant;
	$.ajax({
		type : "POST",
		url : "approvereq",
		data : {
			itemid : i,
			itemName : j,
			emailid : k,
			requestid : l,
			quant:m
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
function requestdecline(itemid, itemName, emailid, requestid) {
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

function setvals(itemId, itemName, itemQuantities, itemDescription) {
	$("#itemId").val(itemId);
	$("#itemName").val(itemName);
	$("#itemQuantities").val(itemQuantities);
	$("#itemDescription").val(itemDescription);
	$('#myModal2').modal('show');
}

function validateEmail(emailField){
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if (reg.test(emailField.value) == false) 
	{
		alert('Invalid Email Address');
		return false;
	}

	return true;

}

