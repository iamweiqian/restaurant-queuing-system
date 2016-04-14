<!DOCTYPE html>
<html>
<head>
	<title>Order Food</title>
</head>
<body>
<form action="OrderFood.php" method="post">
	<table>
		<tr>
			<td>Total Price: </td>
			<td><input type="text" name="total_price" /></td>
		</tr>
		<tr>
			<td>Quantity: </td>
			<td><input type="text" name="quantity" /></td>
		</tr>
		<tr>
			<td>Payment Status: </td>
			<td><input type="text" name="payment_status" /></td>
		</tr>
		<tr>
			<td>Username: </td>
			<td><input type="text" name="username" /></td>
		</tr>
		<tr>
			<td>Menu ID: </td>
			<td><input type="text" name="menu_id" /></td>
		</tr>
	</table>
	<input type="submit" value="Submit">
</form>

</body>
</html>