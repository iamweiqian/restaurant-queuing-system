
<?php
	require "init.php";
	$hpno = $_POST['hpno'];
	$password = $_POST['password'];

	$sql_query = "SELECT * FROM User WHERE hpno = '$hpno' AND password = '$password';";
	$result = mysqli_query($con, $sql_query) or die("Error: ".mysqli_error($con));

	if (!$result) {
		$response = array();
		$code = "reg_false";
		$message = "Server error occurred. Try again.";
		array_push($response, array("code"=>$code, "message"=>$message));
		echo json_encode(array("server_response"=>$response));
	} else {
		// $response = array();
		// $code = "reg_true";
		// $message = "Update Successfully.";
		// array_push($response, array("code"=>$code, "message"=>$message));
		// echo json_encode(array("server_response"=>$response));
		header("Location: http://rprqs.16mb.com/SearchOrder.php");
    exit;
	}
	mysqli_close($con);
?>
