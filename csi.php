
<?php
require_once 'config.php';
$db_link = mysqli_connect($db_hostname, $db_username, $db_password, $db_database); //connecting to$

if(!$db_link){
        die("CONNECTION FAILED: ".mysqli_connect_error());
}

$sql = "SELECT * FROM CSI";
$checkValues = mysqli_query($db_link,$sql);
if(mysqli_num_rows($checkValues > 0){
        while($row = mysqli_fetch_assoc($checkValues)){
                echo "CSI_name: ".$row["CSI_name"];
        }
}

$db_link->close();

?>

