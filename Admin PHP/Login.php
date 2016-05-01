<!DOCTYPE html>
<html>
<head>
	<title>Admin Login</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<h1><center>
  <p>&nbsp;</p>
  <p>Restaurant Parit Raja Management System</p>
  <p>&nbsp;</p>
</center></h1>
<form action="LoginRequest(Admin).php" method="POST">
	<table class="center">
		<tr>
			<td>Username: </td>
			<td><input type="text" name="hpno" /></td>
        </tr>
        <tr>
      		<td>Password: </td>
			<td><input type="text" name="password" /></td>
		</tr>
        <tr>
            <td colspan=2><input type="submit" value="Log in"></td>
        </tr>
  </table>
	<p>&nbsp;</p>
</form>

</body>
</html>
