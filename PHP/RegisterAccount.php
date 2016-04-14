<?php
	require "init.php";
	$username = $_POST['username'];
	$password = $_POST['password'];
	$name = $_POST['name'];
	$hpno = $_POST['hpno'];
	$user_state = $_POST['user_state'];

	$query = "SELECT * FROM User WHERE username LIKE '".$username."';";
	$result = mysqli_query($con, $query);

	if (mysqli_num_rows($result) > 0) {
		$response = array();
		$code = "reg_false";
		$message = "This username is unavailable.";
		array_push($response, array("code"=>$code, "message"=>$message));
		echo json_encode(array("server_response"=>$response));
	} else {
		$sql_query = "INSERT INTO User VALUES('$username', '$password', '$name', '$hpno', '$user_state')";
		$result = mysqli_query($con, $sql_query);

		if (!$result) {
			$response = array();
			$code = "reg_false";
			$message = "Server error occurred. Try again.";
			array_push($response, array("code"=>$code, "message"=>$message));
			echo json_encode(array("server_response"=>$response));
		} else {
			$response = array();
			$code = "reg_true";
			$message = "Registration Success.";
			array_push($response, array("code"=>$code, "message"=>$message));
			echo json_encode(array("server_response"=>$response));
		}
		mysqli_close($con);
	}
?>