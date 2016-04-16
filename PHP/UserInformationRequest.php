<?php
	require "init.php";
    
    $username = $_POST["username"];
    
    $statement = mysqli_prepare($con, "SELECT name, hpno FROM `User` WHERE username = ?") or die(mysqli_error($con));
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $name, $hpno);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["name"] = $name;
        $response["hpno"] = $hpno;
    }
    
    echo json_encode($response);
?>