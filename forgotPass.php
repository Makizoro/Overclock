<?php
require_once 'config.php';
        $db_link=mysqli_connect($db_hostname,$db_username,$db_password,$db_database);
        if(!$db_link){
                die("CONNECTION FAILED: ".mysqli_connect_error());
        }
        $PERSON_id=$_POST["studentId"];
        echo "GO TO THE SYSTEM DOMAIN TO CHANGE YOUR PASSWORD."

?>
