
<?php
require_once 'config.php';

$db_link = mysqli_connect($db_hostname, $db_password, $db_database); //connecting to$

//COLLECTING DATA FROM THE FORM

$PERSON_username=$_POST["Username"];
$PERSON_email=$_POST["Email"];
$PERSON_password=$_POST["UserPassword"];
$CSI_name=$_POST["CSI_name"];//IF THEY BELONG TO A CLUB
$REGISTRATION_date = date("l jS \of F Y h:i:s A");
$PERSON_type=$_POST["PERSON_type"]

//hashing the password
$PERSON_password_hash = password_hash($PERSON_password,PASSWORD_DEFAULT);

if(!$db_link){
        die("CONNECTION FAILED: ".mysqli_connect_error());
}

// FIRST CHECK IF THE USER DOESN'T ALREADY EXIST
$mysql_checkUser="SELECT * FROM PERSON WHERE PERSON_username='$PERSON_username'";
$checkResult = mysqli_query($db_link,$mysql_checkUser);
if(mysqli_num_rows($checkResult) <= 0){ // if the user doesn't exist insert

        // inserting the details to Person table
        $mysql_insertion_into_personTable="INSERT INTO PERSON(
                PERSON_username,PERSON_email,PERSON_password_hash,CSI_name,PERSON_type)
                                        VALUES(
                '$PERSON_username','$PERSON_email','$PERSON_password_hash','$CSI_name','$PERSON_type')";
        
    
                if(mysqli_query(mysqli_query($db_link,$mysql_insertion_into_personTable))){
                    echo "0";
                }
                else{
                    echo "1" .$db_link->error;
                }
}
else{
    echo "1";
}
    
$db_link->close();
    
?>

