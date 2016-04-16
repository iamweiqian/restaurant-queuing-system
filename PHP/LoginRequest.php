<?php
	require "init.php";
	require "password.php";
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM User WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colNo, $colUsername, $colPassword, $colName, $colHpno, $colUser_state);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)) {
        if (password_verify($password, $colPassword)) {
        	$response["success"] = true;
        	$response["username"] = $colUsername;
        	$response["name"] = $colName;
        	$response["hpno"] = $colHpno;
        } 
    };
    
    echo json_encode($response);
?>