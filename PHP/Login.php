<?php
	require "init.php";
	$username = $_POST['username'];
	$password = $_POST['password'];

	$sql_query = "SELECT * FROM User WHERE username LIKE '$username' and password LIKE '$password';";

	$result = mysqli_query($con, $sql_query);

	if (mysqli_num_rows($result) > 0) {
		$row = mysqli_fetch_assoc($result);
		$username = $row["username"];
		// echo "Login Success. Welcome ".$fullname;
	} else {
		echo "Login Failed. Try again";
	}
?>