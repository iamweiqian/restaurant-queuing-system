<?php
$host = "Localhost";
$user = "root";
$password = "7W6Q2Y3a2p3";
$db = "rprqs";

$con = mysqli_connect($host, $user, $password, $db);
if (!$con) {
	die("Error in connection".mysqli_connect_error());
}
?>
