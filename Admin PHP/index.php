<!DOCTYPE html>
<html>
<head>
	<title>Restaurant Parit Raja Management System</title>
	<link rel="stylesheet" type="text/css" href="style.css">
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

<form>
	<table width="793" class="center">
		<tr>
			<th width="40">No.</th>
			<th width="74">Order ID</th>
			<th width="118">Customer's Name</th>
			<th width="182">Food Name</th>
			<th width="75">Quantity</th>
			<th width="96">Total Price (RM)</th>
      <th width="79">Order Time</th>
			<th width="93">Payment Status</th>
		</tr>
		<?php
    		$i = 0;
    		require "init.php";
    		$order_id = $_POST['order_id'];

   			$sql = "SELECT o.*, u.name, m.food_name FROM `Order` o INNER JOIN `User` u ON o.FK_hpno = u.hpno INNER JOIN `Menu` m ON o.FK_menu_id = m.menu_id WHERE DATE(`ordered_on`) = CURDATE()";
   			$result = mysqli_query($con, $sql) or die("Error: ".mysqli_error($con));

    		while ($row = mysqli_fetch_array($result)) {
        		$i++;
        		?>
        		<tr>
          			<td><?php echo $i;?></td>
          			<td><?php echo $row{'order_id'};?></td>
          			<td><?php echo $row{'name'};?></td>
          			<td style="text-align:left"><?php echo $row{'food_name'};?></td>
          			<td><?php echo $row{'quantity'};?></td>
          			<td><?php echo $row{'total_price'};?></td>
          			<td><?php echo $row{'ordered_on'};?></td>
					<td><?php echo $row{'payment_status'};?></td>
        		</tr>
    			<?php }
		?>
	</table>
</form>

</body>
</html>
