<?php
	require "init.php";
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM User WHERE username = '$username' AND password = '$password'");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $no, $username, $password, $name, $hpno, $user_state);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["username"] = $username;
        $response["password"] = $password;
        $response["name"] = $name;
        $response["hpno"] = $hpno;
        $response["user_state"] = $user_state;
    }
    
    echo json_encode($response);
?>