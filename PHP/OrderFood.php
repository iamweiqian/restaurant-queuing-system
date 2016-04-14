<?php
	include('init.php');

	$total_price = $_POST['total_price'];
	$quantity = $_POST['quantity'];
	$payment_status = $_POST['payment_status'];
	$username = $_POST['username'];
	$menu_id = $_POST['menu_id'];
	
	$sql_query = "INSERT INTO `Order` (`total_price`, `quantity`, `payment_status`, `username`, `menu_id`) VALUES ('$total_price', '$quantity', '$payment_status', '$username', '$menu_id');";
	
	if (mysqli_query($con, $sql_query)) 
		echo "<h3>Data Insertion Seccessful...</h3>";
	else
		echo ("Data insertion error...".mysqli_error($con));
	
?>