<?php
	require "init.php";
	$order_id = $_POST['order_id'];
	$payment_status = $_POST['payment_status'];

	$sql_query = "UPDATE `Order` SET payment_status = '$payment_status' WHERE order_id = '$order_id'";
	$result = mysqli_query($con, $sql_query) or die("Error: ".mysqli_error($con));

	if (!$result) {
		$response = array();
		$code = "reg_false";
		$message = "Server error occurred. Try again.";
		array_push($response, array("code"=>$code, "message"=>$message));
		echo json_encode(array("server_response"=>$response));
	} else {
		header("Location: http://rprqs.16mb.com/");
    exit;
	}
	mysqli_close($con);
?>
