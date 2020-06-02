
<?php
require_once 'config.php';
$db_link = mysqli_connect($db_hostname, $db_username, $db_password, $db_database); //connecting to$

if(!$db_link){
        die("CONNECTION FAILED: ".mysqli_connect_error());
}

$CSI = $_POST["CSI"]

$sql = "SELECT CSI_description FROM CSI WHERE CSI_NAME = '$CSI'";
$checkValues = mysqli_query($db_link,$sql);
if(mysqli_num_rows($checkValues > 0){
        while($row = mysqli_fetch_assoc($checkValues)){
                echo "CSI_description: ".$row["CSI_description"];
        }
}

$db_link->close();

?>

