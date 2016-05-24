<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="../../assets/ico/favicon.png">
	<meta http-equiv="refresh" content="<?php echo $sec?>;URL='<?php echo $page?>'">

	<title>Restaurant Parit Raja Management System</title>

	<!-- Bootstrap core CSS -->
	<link href="css/theme.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="jumbotron-narrow.css" rel="stylesheet">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="../../assets/js/html5shiv.js"></script>
	<script src="../../assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>

	<div class="container">
		<div class="header">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="rprqs_index.php">Order</a></li>
				<li><a href="sales.php">Sales</a></li>
			</ul>
			<h3 class="text-muted">Restaurant Parit Raja Management System</h3>
		</div>

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
		<form action="update_request.php" method="POST">
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
						<td><?php echo number_format((float)$row{'total_price'}, 2, '.', '');?></td>
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

			<div class="footer">
				<p>&copy; Restaurant Parit Raja</p>
			</div>

		</div> <!-- /container -->


		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
	</body>
	</html>
