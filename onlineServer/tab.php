<?php
$db = "project";
//$user =$_POST["user_name"];
//$pass =$_POST["password"];
$type = "club";

$conn = mysqli_connect("localhost:3308", "root", "", $db);
//$user = "terry";
//$pass = "ping123";
//$csi = "club";
//$conn =  mysqli_connect("localhost:3308", "root", "", $db);

//if($conn)
//{
	//echo "Connected successfully!";
	$q = "SELECT CSI_name FROM CSI WHERE CSI_type = '$type'";
	//$q = "SELECT * FROM CSI";
	
	$result = mysqli_query($conn, $q);
	
	$response = array();
	while($row = mysqli_fetch_array($result))
	{
		
		array_push($response,array("CSI_name"=>$row[0]));// "CSI_type"=>$row[1], "CSI_email" =>$row[2], "CSI_image"=>$row[3],"CSI_external_link"=>$row[4],"CSI_venue"=>$row[5],"CSI_description"=>$row[6]));
		
		echo json_encode(array("server_response"=>$response));
	}
	
//}
//else
//{
	//echo "Connection Failed.....";
//}
//mysqli_close($conn);
?>