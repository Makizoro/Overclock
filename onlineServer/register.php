<?php
$db = "project";
$user =$_POST["userName"];
$pass =$_POST["password"];
$email =$_POST["email"];
//$CSI_name = $_POST["csiName"];
$type = "regular";

$conn =  mysqli_connect("localhost:3308", "root", "", $db);




if($conn)
{
	
	
	$o = "SELECT PERSON_username,PERSON_password_hash FROM PERSON WHERE PERSON_username='$user' AND PERSON_password_hash ='$pass'";
	$result = mysqli_query($conn, $o);
	
	if(mysqli_num_rows($result) > 0)
	{
		
		echo "username or email already exists" ;
	}
	else
	{

		$q = "INSERT INTO PERSON(PERSON_username, PERSON_email, PERSON_password_hash, CSI_name, PERSON_type) VALUES ('$user', '$email', '$pass', 'gym','$type')";
		$res = mysqli_query($conn, $q);
	
		if($res == True)
		{
			echo "Registration successful..";
		}
		

	}
}
else
{
	echo "Connection Failed.....";
}
?>