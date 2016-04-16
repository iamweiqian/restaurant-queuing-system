<?php
	require "init.php";
    
    $menu_id = $_POST["menu_id"];
    
    $statement = mysqli_prepare($con, "SELECT food_name FROM `Menu` WHERE menu_id = ?") or die(mysqli_error($con));
    mysqli_stmt_bind_param($statement, "s", $menu_id);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $food_name);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["food_name"] = $food_name;
    }
    
    echo json_encode($response);
?>