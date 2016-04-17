<?php
	require "init.php";
	require "password.php";

	$username = $_POST['username'];
	$password = $_POST['password'];

	$passwordHash = password_hash($password, PASSWORD_DEFAULT);
	$statement = mysqli_prepare($con, "INSERT INTO User (password) VALUES ($password) WHERE username = '$username'");
    mysqli_stmt_bind_param($statement, "s", $passwordHash);
    mysqli_stmt_execute($statement);
    mysqli_stmt_close($statement);

    $response = array();
    $response["success"] = true;
    
    echo json_encode($response);
?>