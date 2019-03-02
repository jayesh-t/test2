<?php
/**
 * @USE :
 * This file is use to save or update the employee record.
 * 
 * @Require :
 * connection.php file
 * employee id must comes from $_GET
 * employee name must comes from $_GET
 * city must comes from $_GET
 * number must comes from $_GET
 * salary must comes from $_GET
 * 
 * 
 * @Output :
 * If employee record update successfully then it echo the massage ok.
 * This massage used in javascript for knowing the employee delete successfully or not.
 * 
 * @Process :
 * Get the id, name, city, number, salary from $_GET
 * Creating query for update employee.
 * Executing the query.
 * If query execute sucessfully then print ok massage. 
 */

include '../connection.php';
$id = $_POST['id'];
$name = $_POST['ename'];
$city = $_POST['city'];
$number = $_POST['number'];
$salary = $_POST['salary'];

$query = "UPDATE employee SET ename='$name', city='$city', number='$number', salary='$salary' WHERE id=$id ";
$row = mysqli_query($connect, $query) or die(mysqli_error($connect)); 
if($row) {
    echo "ok";
}
else {
    echo "<h1>Something went wrong. Please try again.</h1>";
}
?>