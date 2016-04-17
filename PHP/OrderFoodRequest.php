<?php
	require "init.php";
	$total_price = $_POST['total_price'];
	$quantity = $_POST['quantity'];
	$payment_status = $_POST['payment_status'];
	$username = $_POST['username'];
	$menu_id = $_POST['menu_id'];
	$ordered_on = $_POST['ordered_on'];

    $statement = mysqli_prepare($con, "INSERT INTO `Order` (total_price, quantity, payment_status, username, menu_id, ordered_on) VALUES (?, ?, ?, ?, ?, ?)") or die(mysqli_error($con));
    mysqli_stmt_bind_param($statement, "dissss", $total_price, $quantity, $payment_status, $username, $menu_id, $ordered_on);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>