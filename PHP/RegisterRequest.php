<?php
	require "init.php";
	require "password.php";

	$username = $_POST['username'];
	$password = $_POST['password'];
	$name = $_POST['name'];
	$hpno = $_POST['hpno'];
	$user_state = $_POST['user_state'];

	function registerUser() {
		global $con, $username, $password, $name, $hpno, $user_state;
		$passwordHash = password_hash($password, PASSWORD_DEFAULT);
		$statement = mysqli_prepare($con, "INSERT INTO User (username, password, name, hpno, user_state) VALUES (?, ?, ?, ?, ?)");
    	mysqli_stmt_bind_param($statement, "ssssi", $username, $passwordHash, $name, $hpno, $user_state);
    	mysqli_stmt_execute($statement);
    	mysqli_stmt_close($statement);
	}
    
    function usernameAvailable() {
    	global $con, $username;
    	$statement = mysqli_prepare($con, "SELECT * FROM User WHERE username = ?");
    	mysqli_stmt_bind_param($statement, "s", $username);
    	mysqli_execute($statement);
    	mysqli_stmt_store_result($statement);
    	$count = mysqli_stmt_num_rows($statement);
    	mysqli_stmt_close($statement);
    	if ($count < 1) {
    		return true;
    	} else {
    		return false;
    	}
    }

    $response = array();
    $response["success"] = false;

    if (usernameAvailable()) {
      	registerUser();
      	$response["success"] = true;
    }  
    
    echo json_encode($response);
?>