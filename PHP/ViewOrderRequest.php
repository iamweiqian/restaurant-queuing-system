<?php
	require "init.php";

    $hpno = $_POST['hpno'];

    $statement = mysqli_query($con, "SELECT o.order_id, o.payment_status, o.ordered_on, m.food_name, m.waiting_time FROM `Order` o INNER JOIN `Menu` m ON o.FK_menu_id = m.menu_id WHERE o.FK_hpno = '$hpno' ORDER BY o.ordered_on DESC") or die(mysqli_error($con));
    $response = array();

    while($row = mysqli_fetch_array($statement)){
        array_push($response, array("order_id"=>$row[0], "payment_status"=>$row[1], "ordered_on"=>$row[2], "food_name"=>$row[3], "waiting_time"=>$row[4]));
    }

    echo json_encode(array("OrderList"=>$response));
?>
