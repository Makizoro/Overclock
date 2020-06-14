<?php
$db = "project";
&CSI_name;
$CSI_type;
$CSI_email;
$CSI_image;
$CSI_external_link;
$CSI_venue; 
$CSI_description

$conn = mysqli_connect("localhost:3308", "root", "", $db);
//$user = "terry";
//$pass = "ping123";

//$conn =  mysqli_connect("localhost:3308", "root", "", $db);

if($conn)
{
	//echo "Connected successfully!";
	$q = "INSERT INTO CSI(CSI_name,CSI_type,CSI_email, CSI_image, CSI_external_link, CSI_venue, CSI_description) VALUES ()'";
	$result = mysqli_query($conn, $q);
	
	if(mysqli_num_rows($result) > 0)
	{
		$row = mysqli_fetch_assoc($result);
		$name = $row["PERSON_type"];
	//	echo $name;
		
		if($name == $type){
			echo "Login Succesful..Admin";
		}else{
			echo "Login Succesful";
		}
	}
	else
	{
		echo "Login Failed......Try Again..";
	}
}
else
{
	//echo "Connection Failed.....";
}
?>