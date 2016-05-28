<?php
require "init.php";
require "password.php";

$hpno = $_POST['hpno'];
$password = $_POST['password'];
$name = $_POST['name'];
$user_state = $_POST['user_state'];

function registerUser() {
	global $con, $hpno, $password, $name, $user_state;
	$passwordHash = password_hash($password, PASSWORD_DEFAULT);
	$statement = mysqli_prepare($con, "INSERT INTO User (hpno, password, name, user_state) VALUES (?, ?, ?, ?)");
	mysqli_stmt_bind_param($statement, "sssi", $hpno, $passwordHash, $name, $user_state);
	mysqli_stmt_execute($statement);
	mysqli_stmt_close($statement);
}

function hpnoAvailable() {
	global $con, $hpno;
	$statement = mysqli_prepare($con, "SELECT * FROM User WHERE hpno = ?");
	mysqli_stmt_bind_param($statement, "s", $hpno);
	mysqli_execute($statement);
	mysqli_stmt_store_result($statement);
	$count = mysqli_stmt_num_rows($statement);
	mysqli_stmt_close($statement);
	if ($count < 1) {
		return true;
	} else {
		return false;
	}
}

$response = array();
$response["success"] = false;

if (hpnoAvailable()) {
	registerUser();
	$response["success"] = true;
}

echo json_encode($response);
?>
