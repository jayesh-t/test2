<?php
/**
 * @USE :
 * This file is use for Showing employee name.
 * 
 * @Require :
 * connection.php file
 * employee-info.php file
 * employee id must comes from $_GET
 * 
 * @Output :
 * Prints the employee name.
 * This name used in javascript.
 * 
 * @Process :
 * Get the employee id from $_GET
 * Calling getEmployee(id) and storing return value in $name. This function return a name.
 * Printing the name of employee.
 */

include '../connection.php';
include '../employee-info.php';
$id = $_GET['id'];
$name = getEmployeeName($id);
echo $name;
?>