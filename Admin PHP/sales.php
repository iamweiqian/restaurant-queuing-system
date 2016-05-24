<?php
require "init.php";

$sql = "SELECT o.*, m.food_name FROM `Order` o INNER JOIN `Menu` m ON m.menu_id = o.FK_menu_id WHERE DATE(`ordered_on`) = CURDATE()";
$resultList = mysqli_query($con, $sql) or die("Error: ".mysqli_error($con));
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="shortcut icon" href="../../assets/ico/favicon.png">

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

  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">

  // Load the Visualization API and the corechart package.
  google.charts.load('current', {'packages':['corechart']});

  // Set a callback to run when the Google Visualization API is loaded.
  google.charts.setOnLoadCallback(drawChart);

  // Callback that creates and populates a data table,
  // instantiates the pie chart, passes in the data and
  // draws it.
  function drawChart() {
    // Create the data table.
    var data = google.visualization.arrayToDataTable([
      ['Food Name', 'Quantity'],
      <?php foreach($resultList as $result) : ?>
      ['<?php echo $result['food_name'];?>', <?php echo $result['quantity'];?>],
      <?php endforeach; ?>
    ]);

    // Set chart options
    var options = {'title':"Today's Sales",
    'width':600,
    'height':450,
    'legend':'none'};

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    chart.draw(data, options);
  }
  </script>
</head>

<body>

  <div class="container">
    <div class="header">
      <ul class="nav nav-pills pull-right">
        <li><a href="rprqs_index.php">Order</a></li>
        <li class="active"><a href="sales.php">Sales</a></li>
      </ul>
      <h3 class="text-muted">Restaurant Parit Raja Management System</h3>
    </div>

    <div class="jumbotron">
      <!--Div that will hold the pie chart-->
      <div id="chart_div" align="center"></div>
    </div>

    <div class="footer">
      <p>&copy; Restaurant Parit Raja</p>
    </div>

  </div> <!-- /container -->


  <!-- Bootstrap core JavaScript
  ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
