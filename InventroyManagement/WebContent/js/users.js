function itemSelected(itemname) {
		/*  $("#descriptiondiv").hide(); */
	
		var iname = itemname;
		var btname = document.getElementById("dropitem");
		$("#quantdrop").text(iname);

		$.ajax({
			type : "POST",
			url : "description",
			dataType : 'html',
			data : {
				itemName : iname,
			},
			success : function(result) {
				/* console.log(result); */
				$("#descriptiondiv").html('');
				$("#requestbtn").html('');
				quantSelected(iname);
				
				$("#descriptiondiv").show();
				$("#requestbtn").show();
				$("#descriptiondiv").append("<h3>Description</h3><p><b>" + result + "</b></p>");
				$("#requestbtn").append('<button type="button" class="btn btn-success btn-xs" onclick="makeRequest(\''+iname+'\');">Request Now</button>');
				
				

			}
		});

	}
	
	 function quantSelected(itemname) {
			/*  $("#descriptiondiv").hide(); */
			var iname = itemname;
		
			//var quantity=quant;
			//var btname = document.getElementById("dropitem");
			

			$.ajax({
				type : "POST",
				url : "quantity",
				dataType : 'html',
				data : {
					itemName : iname,
				},
				success : function(result) {
				/* 	console.log(result); */
					/* $("#textdiv").html('');  */
				 $("#display").html(''); 
				/* $("#descriptiondiv").html(''); */
				 $("#display").show(); 
				 $("#textdiv").show(); 
					$("#display").append("<p><b>" + result + "</b></p>");

				}
			});

		}
	 function makeRequest(iname) {
			$("#loader").fadeIn();
			var itemName = iname;
			var quant=document.getElementById('quantreq').value;
			console.log(quant);
			$.ajax({
				type : "POST",
				url : "newrequest",
				data : {
					itemName : itemName,quant : quant,
				},
				success : function(result) {
					$("#loader").fadeOut();
					$("#ApproveDecline").modal('show');
					
				}
			});

		}