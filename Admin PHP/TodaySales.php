<html>
  <head>
    <title>Today's Sales</title>
  	<link rel="stylesheet" type="text/css" href="style.css">
    <!--Load the AJAX API-->
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
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Food');
        data.addColumn('number', 'Quantity');
        data.addRows([
          <?php
            $i = 0;
            require "init.php";
            $menu_id = "S001";

            //query db
            $sql = "SELECT m.food_name, SUM(o.quantity) AS quantity FROM `Order` o INNER JOIN `Menu` m ON m.menu_id = o.FK_menu_id WHERE DATE(`ordered_on`) = CURDATE() AND o.FK_menu_id = '$menu_id'";
            $result = mysqli_query($con, $sql) or die("Error: ".mysqli_error($con));

            while ($row = mysqli_fetch_array($result)) {
              $i++;
              echo "['".$row['food_name']."', ".$row['quantity']."]";
              if ($i < $row) {
                echo ",";
              }
            }
          ?>
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
  	<h1><center>
 	 <p>&nbsp;</p>
 	 <p>Restaurant Parit Raja Management System</p>
 	 <p>&nbsp;</p>
	</center></h1>
    <!--Div that will hold the pie chart-->
    <div id="chart_div" align=""></div>
  </body>
</html>
