<?php
require_once 'config.php';
$db_link = mysqli_connect($db_hostname, $db_username, $db_password, $db_database); //connect$

if(!$db_link){
        die("CONNECTION FAILED: ".mysqli_connect_error());
}

$sql = "SELECT * FROM CLUB";
$checkValues = mysqli_query($db_link,$sql);
if(mysqli_num_rows($checkValues > 0){
        while($row = mysqli_fetch_assoc($checkValues)){
                echo "CLUB_name: ".$row["CLUB_name"];
        }
}

$db_link->close();

?>



















                                      [ Read 20 lines ]
^G Get Help    ^O Write Out   ^W Where Is    ^K Cut Text    ^J Justify     ^C Cur Pos
^X Exit        ^R Read File   ^\ Replace     ^U Uncut Text  ^T To Spell    ^_ Go To Line
