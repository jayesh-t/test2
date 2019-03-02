<?php
/**
 * @USE :
 * This file is use for cancel the money transaction.
 * 
 * @Require :
 * connection.php file
 * employee-info.php file -- This file use some function of employee-info.php file
 * For canceling the transaction it required a transaction id.
 * Transaction id should be send with GET method with transactionId name e.g cancel-transaction.php?transactionId=1
 * 
 * @Output :
 * If transaction cancel successfully the it echo the massage ok.
 * This massage used in javascript for knowing the transaction cancel successfully or not. 
 * 
 * @Process :
 * Getting the money tranasction data of given id from database and in $row array.
 * Now update the employee balance using updateEmployeeBalance() function. Balance updation is neccessary.
 * Finally deleting the money transaction.
 */


// include connection file
include '../../connection.php';
// include employee info
include '../../employee-info.php';

// Getting the transaction id
$id = $_GET['transactionId'];

// Query for getting money transaction data from databse
$query = "SELECT * FROM money_transaction WHERE id=$id";
// Executing the query
$result = mysqli_query($connect, $query);
// Fetching data in array format
$row = mysqli_fetch_array($result);

// Updating the balance.
updateEmployeeBalance($row['emp_id'], $row['amount']);
// Deleting the  money transaction
deleteMoneyTransaction($id);

if(isset($_GET['transaction2'])) {
    $id = $_GET['transaction2'];

    $query = "SELECT * FROM money_transaction WHERE id=$id";
    $result = mysqli_query($connect, $query);
    $row = mysqli_fetch_array($result);

    updateEmployeeBalance($row['emp_id'], $row['amount']);
    deleteMoneyTransaction($id);
}
// Printing the ok massage that helps to javascript function to check transaction cancellation success or not
echo "ok";
?>