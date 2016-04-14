<?php
	require "init.php";
	$total_price = $_POST['total_price'];
	$quantity = $_POST['quantity'];
	$payment_status = $_POST['payment_status'];
	$username = $_POST['username'];
	$menu_id = $_POST['menu_id'];

    $statement = mysqli_prepare($con, "INSERT INTO User (total_price, quantity, payment_status, username, menu_id) VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "iisss", $total_price, $quantity, $payment_status, $username, $menu_id);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>