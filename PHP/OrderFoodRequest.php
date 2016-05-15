<?php
	require "init.php";
	$total_price = $_POST['total_price'];
	$quantity = $_POST['quantity'];
	$payment_status = $_POST['payment_status'];
	$hpno = $_POST['hpno'];
	$menu_id = $_POST['menu_id'];
	$ordered_on = $_POST['ordered_on'];
	$ready_on = $_POST['ready_on'];

  $statement = mysqli_prepare($con, "INSERT INTO `Order` (total_price, quantity, payment_status, ordered_on, ready_on, FK_hpno, FK_menu_id) VALUES (?, ?, ?, ?, ?, ?, ?)") or die(mysqli_error($con));
  mysqli_stmt_bind_param($statement, "disssss", $total_price, $quantity, $payment_status, $ordered_on, $ready_on, $hpno, $menu_id);
  mysqli_stmt_execute($statement);

  $response = array();
  $response["success"] = true;

    echo json_encode($response);
?>
