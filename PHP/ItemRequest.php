<?php
	require "init.php";
    
    $menu_id = $_POST["menu_id"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM Menu WHERE menu_id = ?");
    mysqli_stmt_bind_param($statement, "s", $menu_id);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $no, $menu_id, $food_name, $description, $basic_price, $waiting_time);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["menu_id"] = $menu_id;
        $response["food_name"] = $food_name;
        $response["description"] = $description;
        $response["basic_price"] = $basic_price;
    }
    
    echo json_encode($response);
?>