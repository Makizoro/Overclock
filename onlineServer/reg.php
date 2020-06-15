<?php
$db = "project";
//$descinfo[3] = {"","",""};
//$csi_name = $_POST["]

$conn = mysqli_connect("localhost:3308", "root", "", $db);


if($conn)
{
	
	$q = "SELECT * FROM newCSI";
	
	$result = mysqli_query($conn, $q);
	
	if(mysqli_num_rows($result) > 0)
	{
		$row = mysqli_fetch_assoc($result);
		$desc = $row["CSI_description"];
        $name = $row["CSI_name"];
		$response = array();
		while($row = mysqli_fetch_array($result))
		{
			
			array_push($response,array($row));//,$desc[1]=>$row[1],$desc[2]=>$row[2],));
		}
		
		//echo json_encode(array($response));
		
        echo $name;
		echo $desc;
	}
	else
	{
		echo "Failed";
	}
}
mysqli_close($conn);
?>
