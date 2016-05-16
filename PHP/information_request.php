<?php
require "init.php";

$order_id = $_POST['order_id'];
$hpno = $_POST['hpno'];

$statement = mysqli_prepare($con, "SELECT o.total_price, o.quantity, o.payment_status, o.ordered_on, u.name, m.food_name FROM `Order` o INNER JOIN `User` u ON o.FK_hpno = u.hpno INNER JOIN `Menu` m ON o.FK_menu_id = m.menu_id WHERE o.order_id = ? AND o.FK_hpno = ?");
mysqli_stmt_bind_param($statement, "ss", $order_id, $hpno);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $total_price, $quantity, $payment_status, $ordered_on, $name, $food_name);

$response = array();
$response["success"] = false;

while(mysqli_stmt_fetch($statement)){
	$response["success"] = true;
	$response["total_price"] = $total_price;
	$response["quantity"] = $quantity;
	$response["payment_status"] = $payment_status;
	$response["ordered_on"] = $ordered_on;
	$response["name"] = $name;
	$response["food_name"] = $food_name;
}

echo json_encode($response);
?>
