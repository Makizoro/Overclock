
<?php
require_once 'config.php';

$db_link = mysqli_connect($db_hostname, $db_username, $db_password, $db_database);

$password=$_POST["UserPassword"];
$UserName=$_POST["UserName"];
$studentId=$_POST["studentId"];
$club=$_POST["club"];

$hash = password_hash($password,PASSWORD_DEFAULT);

if(!$db_link){
	die("CONNECTION FAILED: ".mysqli_connect_error());
}
$mysql_qry="INSERT INTO register(username,student_number,password,club) VALUES('$UserName','$studentId','$hash','$club')";

$mysql_qry1="INSERT INTO USERS(STUDENT_ID,PASSWORD) VALUES('$studentId','$hash')";
 

if(mysqli_query($db_link,$mysql_qry) && mysqli_query($db_link,$mysql_qry1))
{
   echo "register success";
}
else{
	echo "register not success" .$db_link->error;
}

$db_link->close();

?>
