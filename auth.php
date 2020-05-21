<?php
require_once 'config.php';
        $db_link=mysqli_connect($db_hostname,$db_username,$db_password,$db_database);
        if(!$db_link){
                die("CONNECTION FAILED: ".mysqli_connect_error());
        }
        $PERSON_username=$_POST["UserName"];
        $PERSON_password=$_POST["UserPassword"];

        $hash="";
        $output ="0";
        $sql = "SELECT PERSON_username,PERSON_password_hash FROM PERSON WHERE PERSON_username='$PERSON_username'";
        $result = mysqli_query($db_link,$sql);
        if(mysqli_num_rows($result) > 0){
                while($row=mysqli_fetch_assoc($result)){
                        $hash=$row["PERSON_password_hash"];
                }
        }
        else{
                echo "0";
        }
        if(password_verify($PERSON_password,$hash)){
                echo "0";
        }
        else{
                echo "1";
        }
        mysqli_close($db_link);

?>
