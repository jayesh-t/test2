<?php
/**
 * @USE :
 * This file is use to pay the salary of employee.
 * 
 * @Require :
 * connection.php file
 * employee-info.php file
 * employee id must comes from $_GET
 * amount must comes from $_GET
 * 
 * @Output :
 * Prints the last inserted recored id.
 * This name used in javascript for canceling transaction.
 * 
 * @Process :
 * Get the employee id and amount from $_GET
 * Calling updateEmployeeBalance(id, amount, false) This false means decrease the employee balance.
 * updateEmployeeBalance function update the employee balance.
 * Calling createMoneyTransaction(id, amount) This function inserting record to money_transaction table.
 * Printing the last inserted recoed id.
 */
include '../connection.php';
include '../employee-info.php';

$id = $_GET['id'];
$amount = $_GET['amt'];

updateEmployeeBalance($id, $amount, false);
createMoneyTransaction($id, $amount);
$last_id = mysqli_insert_id($connect);

//$last_id = 102;
echo $last_id;

?>