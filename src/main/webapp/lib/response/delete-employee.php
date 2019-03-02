<?php
/**
 * @USE :
 * This file is use for deleting employee record.
 * 
 * @Require :
 * connection.php file
 * employee id must be comes from $_GET
 * 
 * @Output :
 * If employee delete successfully then it echo the massage ok.
 * This massage used in javascript for knowing the employee delete successfully or not.
 * 
 * @Process :
 * Deleting employee means not deleting record.
 * It means update employee data like is_working = 'n'
 * 
 * Getting employee id from $_GET
 * Creating query for update employee record and executing the query and store return value in $row variable.
 * If $row > 0 that query sucessfully executed.
 */

include '../connection.php';
$id = $_GET['id'];

$query = "UPDATE employee SET is_working='n' WHERE id=$id ";
$row = mysqli_query($connect, $query) or die(mysqli_error($connect)); 
if($row) {
    echo "ok";
}
else {
    echo "<h1>Something went wrong. Please try again.</h1>";
}
?>