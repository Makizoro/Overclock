<?php
$db = "project";

$conn = mysqli_connect("localhost:3308", "root", "", $db);


if($conn)
{
	
	$q = "SELECT CSI_name FROM CSI ";
	
	$result = mysqli_query($conn, $q);
	
	$response = array();
	while($row = mysqli_fetch_array($result))
	{
		array_push($response,array($row[0]));
	}
	
	echo json_encode(array($response));
	//echo $response;
	echo $response[0][0];
	echo "\n".$response[1][0];
	echo "\n".$response[2][0];
}
mysqli_close($conn);
?>