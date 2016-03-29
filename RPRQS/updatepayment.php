<!DOCTYPE html>
<html>
<head>
	<title>Update Payment Status</title>
</head>
<body>
<form action="UpdatePaymentStatus.php" method="POST">
	<table>
		<tr>
			<th>No.</th>
			<th>Order ID</th>
			<th>Menu ID</th>
			<th>Quantity</th>
			<th>Total Price</th>
			<th>Payment Status</th>
			<th>Action</th>
		</tr>
		<?php
    		$i = 0;
    		require "init.php";
    		$order_id = $_POST['order_id'];

   			$sql = "SELECT * FROM `Order` WHERE order_id LIKE '$order_id'";
   			$result = mysqli_query($con, $sql) or die("Error: ".mysqli_error($con));

    		while ($row = mysqli_fetch_array($result)) {
        		$i++;
        		?>
        		<tr>
          			<td><?php echo $i;?></td>
          			<td><?php echo $row{'order_id'};?></td>
          			<td><?php echo $row{'menu_id'};?></td>
          			<td><?php echo $row{'quantity'};?></td>
          			<td><?php echo $row{'total_price'};?></td>
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