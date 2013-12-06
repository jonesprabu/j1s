
$(document).ready(function(){
	$("#commentForm").submit(function(event) {
		  /* stop form from submitting normally */
		  event.preventDefault();

		  /*clear result div*/
		  resultDiv = $(this).find(".statusMessageDiv");
		  $(resultDiv).html('');
		  
		  /* get some values from elements on the page: */
		   var values = $(this).serialize();
		  /* Send the data using post and put the results in a div */
		    $.ajax({
		      url: "php/MailActions.php",
		      type: "post",
		      data: values,
		      dataType : "json",
		      success: function(data){
		          resultDiv.html(data.message);
		      },
		      error:function(){
		          resultDiv.html("Error sending mail, Please try again later.");
		      }   
		    }); 
		});
	
	/*$("#sendEmail").live('click', function(){
		alert("button clicked");
		$(this).form().submit();
		alert("after form submitted");
	});*/
});