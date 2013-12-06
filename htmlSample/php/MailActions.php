<?php

require_once "Mail.php";

 $email 	= $_POST['customerEmail'] ;
 $message 	= $_POST['customerComments'] ;
 $custName	= $_POST['customerName'];
 $custMobile = $_POST['customerMobile'];


 $from = "admin@arunpipes.com";
 $to = "mail2arunpipes@gmail.com";
 $subject = "Enquiry From : ".$custName."(".$custMobile.")";
 $body = "Customer Name : ".$custName."\n"
 		."Customer Mobile: ".$custMobile."\n"
 		."Message : ".$message;
 
 $host = "mail.arunpipes.com";
 $username = "arunpipe";
 $password = "@run123Pipe";
 
 $headers = array ('From' => $from,
   'To' => $to,
   'Subject' => $subject);
 $smtp = Mail::factory('smtp',
   array ('host' => $host,
     'auth' => true,
     'username' => $username,
     'password' => $password));
 
 $mail = $smtp->send($to, $headers, $body);
 //Sending Acknowledment Mail to the sender.
 $headers = array ('From' => $from,
 		'To' => $email,
 		'Subject' => "Thank you for Contacting Arun pipes.");
 $body = "Hi ".$custName.",\n\n".
 		"Greeting from Arun Pipes. We will get back to you. \n".
 		"Thanks for contacting Arun Pipes.\n\n".
 		"Warm Regards,\n".
 		"Arun Pipes Sales Team.";
 $smtp->send($email, $headers, $body);
 
 $encoded = null;
 $result = array();
 if (PEAR::isError($mail)) {
   //echo("<p>" . $mail->getMessage() . "</p>");
   $result['status'] = 0;
   $result['message']= $mail->getMessage();
   $encoded = json_encode($result);
  } else {
   //echo("<p>Message successfully sent!</p>");
   $result['status'] = 1;
   $result['message']= 'Message successfully sent!';
   $encoded = json_encode($result);
  }
  die($encoded);
?>
