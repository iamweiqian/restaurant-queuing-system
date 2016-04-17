<?php
	require "init.php";
    
    $order_id = $_POST["order_id"];
    $username = $_POST["username"];
    
    $statement = mysqli_prepare($con, "SELECT o.total_price, o.quantity, o.payment_status, o.ordered_on, u.name, u.hpno, m.food_name FROM `Order` o INNER JOIN `User` u ON o.username = u.username INNER JOIN `Menu` m ON o.menu_id = m.menu_id WHERE o.order_id = ? AND o.username = ?");
    mysqli_stmt_bind_param($statement, "ss", $order_id, $username);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $total_price, $quantity, $payment_status, $ordered_on, $name, $hpno, $food_name);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["total_price"] = $total_price;
        $response["quantity"] = $quantity;
        $response["payment_status"] = $payment_status;
        $response["ordered_on"] = $ordered_on;
        $response["name"] = $name;
        $response["hpno"] = $hpno;
        $response["food_name"] = $food_name;
    }
    
    echo json_encode($response);
?>