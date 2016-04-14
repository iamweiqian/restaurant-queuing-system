<?php
	$host = "mysql.hostinger.my";
	$user = "u197760149_admin";
	$password = "Ab123456";
	$db = "u197760149_rprqs";

	$con = mysqli_connect($host, $user, $password, $db);
	if (!$con) {
		die("Error in connection".mysqli_connect_error());
	} else {
		echo "<br><h3>Database connection Success....</h3>";
	}
?>