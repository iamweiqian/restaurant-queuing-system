<!DOCTYPE html>
<html>
<head>
	<title>Update Payment Status</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1><center>
		<p>&nbsp;</p>
		<p>Restaurant Parit Raja Management System</p>
		<p>&nbsp;</p>
	</center></h1>
	<form action="UpdatePayment.php" method="post">
		<table class="center">
			<tr>
				<td>Order ID: </td>
				<td><input type="text" name="order_id" /></td>
				<td><input type="submit" value="Search"></td>
			</tr>
		</table>
		<p>&nbsp;</p>
	</form>
	<form action="UpdatePaymentRequest.php" method="POST">
		<table class="center">
			<tr>
				<th width="48">No.</th>
				<th width="82">Order ID</th>
				<th width="120">Customer's Name</th>
				<th width="100">Phone No.</th>
				<th width="196">Food Name</th>
				<th width="83">Quantity</th>
				<th width="104">Total Price (RM)</th>
				<th width="87">Order Time</th>
				<th width="154">Payment Status</th>
				<th width="69">Action</th>
			</tr>
			<?php
			$i = 0;
			require "init.php";
			$order_id = $_POST['order_id'];

			$sql = "SELECT o.*, u.hpno, u.name, m.food_name FROM `Order` o INNER JOIN `User` u ON o.FK_hpno = u.hpno INNER JOIN `Menu` m ON o.FK_menu_id = m.menu_id WHERE order_id LIKE '$order_id'";
			$result = mysqli_query($con, $sql) or die("Error: ".mysqli_error($con));

			while ($row = mysqli_fetch_array($result)) {
				$i++;
				?>
				<tr>
					<td><?php echo $i;?></td>
					<td><?php echo $row{'order_id'};?></td>
					<td><?php echo $row{'name'};?></td>
					<td><?php echo $row{'hpno'};?></td>
					<td style="text-align:left"><?php echo $row{'food_name'};?></td>
					<td><?php echo $row{'quantity'};?></td>
					<td><?php echo $row{'total_price'};?></td>
					<td><?php echo $row{'ordered_on'};?></td>
					<td><input type="radio" name="payment_status" <?php if($row['payment_status']=="Unpaid") {echo "checked";}?> value="Unpaid">Unpaid
						<input type="radio" name="payment_status" <?php if($row['payment_status']=="Paid") {echo "checked";}?> value="Paid">Paid
					</td>
					<input type="hidden" name="order_id" value="<?php echo $row{'order_id'};?>">
					<td><input type="submit" name="update" value="Update"></td>
				</tr>
				<?php }
				?>
			</table>
		</form>

	</body>
	</html>
