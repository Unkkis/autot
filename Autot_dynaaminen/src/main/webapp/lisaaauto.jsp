<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="scripts/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Lis�� auto</title>
</head>
<body>
<form id="tiedot">
	<table>
		<thead>	
			<tr>
				<th colspan="5" class="oikealle"><span id="takaisin">Takaisin listaukseen</span></th>
			</tr>		
			<tr>
				<th>RekNo</th>
				<th>Merkki</th>
				<th>Malli</th>
				<th>Vuosi</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" name="rekNo" id="rekNo"></td>
				<td><input type="text" name="merkki" id="merkki"></td>
				<td><input type="text" name="malli" id="malli"></td>
				<td><input type="text" name="vuosi" id="vuosi"></td> 
				<td><input type="submit" id="tallenna" value="Lis��"></td>
			</tr>
		</tbody>
	</table>
</form>
<span id="ilmo"></span>

</body>
<script>
$
</script>
</html>