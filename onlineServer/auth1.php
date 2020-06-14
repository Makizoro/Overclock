<?php
$db = "project";
$user =$_POST["user_name"];
$pass =$_POST["password"];
$type = "admin";

$conn = mysqli_connect("localhost:3308", "root", "", $db);
//$user = "terry";
//$pass = "ping123";

//$conn =  mysqli_connect("localhost:3308", "root", "", $db);

if($conn)
{
	//echo "Connected successfully!";
	$q = "SELECT PERSON_username,PERSON_password_hash, PERSON_type FROM PERSON WHERE PERSON_username='$user' AND PERSON_password_hash ='$pass'";
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