<?
	if ( isset($_POST["targetTemp"]) ) {
		// Set a new target temperature
		getRemote("setTargetTemperature","target=".$_POST["targetTemp"]);
	}
	
	$targetTemperature = getRemote("getTargetTemperature");
	$avgTemperature = getRemote("getAverageTemperature");
	
	function getRemote($method,$args="") {
		$modelURL = "http://localhost:8300/";
		// Read current values
		$file = fopen( sprintf("%s%s?%s",$modelURL,$method,$args), "r");
		if ( !$file ) {
			print "Unable to open model URL: $modelURL";
			exit;
		}
		while (!feof ($file)) {
		    $line = $line . fgets ($file, 1024);
		}
		fclose($file);
		return $line;
	}
?>
<html>
<head>
</head>
<body>
	<form method="post">
	<table>
		<tr>
			<td>Current Room Temperature</td>
			<td><?=$avgTemperature?></td>
		</tr>
		<tr>
			<td>Adjust Room Temperature</td>
			<td><input type=text name=targetTemp maxlength="5" value="<?=$targetTemperature?>"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type=submit value="Store"></td>
		</tr>
	</table>
	</form>
</body>
</html>