<?php
$db = "project";
//$descinfo[3] = {"","",""};
//$csi_name = $_POST["]

    $CSI_name = $_POST["SName"];
    $CSI_type = $_POST["type"];
    $CSI_des = $_POST["SDesc"];

$conn = mysqli_connect("localhost:3308", "root", "", $db);


if($conn)
{
    
    $q = "INSERT INTO CSI(CSI_name, CSI_type, CSI_description) VALUES ('$CSI_name', 'CSI_type', 'CSI_des')";
    
    $result = mysqli_query($conn, $q);
    
    $res = mysqli_query($conn, $q);
    
        if($res == True)
        {
            echo "Sent to Administrator for Approval!";
        }
}
mysqli_close($conn);
?>
