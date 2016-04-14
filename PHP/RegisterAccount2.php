<?php
	require "init.php";
	$username = $_POST['username'];
	$password = $_POST['password'];
	$name = $_POST['name'];
	$hpno = $_POST['hpno'];
	$user_state = $_POST['user_state'];

    $statement = mysqli_prepare($con, "INSERT INTO User (username, password, name, hpno, user_state) VALUES ('$username', '$password', '$name', '$hpno', '$user_state')");
    mysqli_stmt_bind_param($statement, "siss", $username, $password, $name, $hpno, $user_state);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>