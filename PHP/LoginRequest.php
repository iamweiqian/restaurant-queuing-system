<?php
	require "init.php";
	require "password.php";
    
    $hpno = $_POST["hpno"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM User WHERE hpno = ?");
    mysqli_stmt_bind_param($statement, "s", $hpno);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colNo, $colHpno, $colPassword, $colName, $colUser_state);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)) {
        if (password_verify($password, $colPassword)) {
        	$response["success"] = true;
        	$response["name"] = $colName;
        } 
    };
    
    echo json_encode($response);
?>