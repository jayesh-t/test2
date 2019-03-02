<?php
/**
 * @USE :
 * This file is use for inserting the employee and employee_balance record.
 * 
 * @Require :
 * connection.php file
 * name, city, number, salary of the employee which must be comes from $_POST variable.
 *  
 * @Output :
 * If record inserted successfully then it echo the massage ok.
 * This massage used in javascript for knowing the record inserted successfully or not. 
 * 
 * @Process :
 * Getting name, city, number, salary from $_POST.
 * Creating the insert employee query and executing the query.
 * Getting the last inserted id from database for inserting the employee balance record. Balance by default 0.
 * These id is neccessary for managing forgien key constraint.
 *  Creating the insert employee_balance query and executing the query.
 * if query suceess the print massage "ok"
 */


// include connection.php file.
include '../connection.php';

// Getting the values comes from form
$name = $_POST['ename'];
$city = $_POST['city'];
$number = $_POST['number'];
$salary = $_POST['salary'];

// Query for inserting record to the employee table.
$query = "INSERT INTO employee (ename, city, number, salary) VALUES ('$name', '$city', '$number', '$salary')";
// Executing the query.
mysqli_query($connect, $query) or die(mysqli_error($connect));
// Getting the id of last inserted record from database.
$id = mysqli_insert_id($connect);
// Query for inserting the record in the employee_balance table.
$query = "INSERT INTO employee_balance (emp_id) VALUES ($id)";
// Executing the query and store value return value in $row
$row = mysqli_query($connect, $query) or die(mysqli_error($connect));

$row = 1;
// If $row > 0 thant means query successfully executed.
if($row) {
    echo "ok";
}
else {
    echo "<h1>Something went wrong. Please try again.</h1>";
} 
?>