<!DOCTYPE html>
<html>
<head>
	<title>Search Order</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
<h1><center>
  <p>&nbsp;</p>
  <p>Restaurant Parit Raja Management System</p>
  <p>&nbsp;</p>
</center></h1>
<form action="updatepayment.php" method="post">
	<table class="center">
		<tr>
			<td>Order ID: </td>
			<td><input type="text" name="order_id" /></td>
            <td><input type="submit" value="Search"></td>
		</tr>
  </table>
	<p>&nbsp;</p>
</form>

<form>
	<table width="721" class="center">
		<tr>
			<th width="40">No.</th>
			<th width="79">Order ID</th>
			<th width="220">Food Name</th>
			<th width="85">Quantity</th>
			<th width="140">Total Price (RM)</th>
			<th width="129">Payment Status</th>
		</tr>
		<?php
    		$i = 0;
    		require "init.php";
    		$order_id = $_POST['order_id'];

   			$sql = "SELECT o.*, m.food_name FROM `Order` o INNER JOIN `Menu` m ON o.FK_menu_id = m.menu_id";
   			$result = mysqli_query($con, $sql) or die("Error: ".mysqli_error($con));

    		while ($row = mysqli_fetch_array($result)) {
        		$i++;
        		?>
        		<tr>
          			<td><?php echo $i;?></td>
          			<td><?php echo $row{'order_id'};?></td>
          			<td style="text-align:left"><?php echo $row{'food_name'};?></td>
          			<td><?php echo $row{'quantity'};?></td>
          			<td><?php echo $row{'total_price'};?></td>
					<td><?php echo $row{'payment_status'};?></td>
        		</tr>
    			<?php }
		?>
	</table>
</form>

</body>
</html>
