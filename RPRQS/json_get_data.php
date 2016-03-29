<?
	require "init.php";

	$sql = "SELECT * FROM User;";

	$result = mysqli_query($con, $sql);

	$response = array();

	while ($row = mysqli_fetch_array($result)) {
		array_push($response, array("username"=>$row[0], "password"=>$row[1], "name"=>$row[2], "hpno"=>$row[3], "user_state"=>$row[4]))
	}

	echo json_encode(array("server_response"=>$response));

	mysqli_close($con);

?>