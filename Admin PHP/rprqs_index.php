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
      <h3 class="text-muted">Restaurant Parit Raja</h3>
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

    <form>
      <table width="939" class="center">
        <tr>
          <th width="36">No.</th>
          <th width="70">Order ID</th>
          <th width="136">Customer's Name</th>
          <th width="96">Phone No.</th>
          <th width="153">Food Name</th>
          <th width="78">Quantity</th>
          <th width="119">Total Price (RM)</th>
          <th width="99">Order Time</th>
          <th width="112">Payment Status</th>
        </tr>
        <?php
        $i = 0;
        require "init.php";
        $order_id = $_POST['order_id'];

        $sql = "SELECT o.*, u.hpno, u.name, m.food_name FROM `Order` o INNER JOIN `User` u ON o.FK_hpno = u.hpno INNER JOIN `Menu` m ON o.FK_menu_id = m.menu_id WHERE DATE(`ordered_on`) = CURDATE() ORDER BY order_id DESC ";
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
            <td><?php echo $row{'total_price'};?></td>
            <td><?php echo $row{'ordered_on'};?></td>
            <td><?php echo $row{'payment_status'};?></td>
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
