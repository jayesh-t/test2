<?php

function getEmployeeSalary($id) {
    /**
     * It is use for getting employee salary.
     * @param id employee id
     * @return salary employee salary
     */

    // Getting connection object.
    $connect = $GLOBALS['connect'];
    // Query for selecting employee record of given id.
    $query = "SELECT * FROM employee WHERE id = $id";
    // Executing query and store result in $result variable.
    $result = mysqli_query($connect, $query) or die(mysqli_error($connect));
    // Fetching data to $row
    $row = mysqli_fetch_array($result);
    // Return the employee salary.
    return $row['salary'];
}

function updateEmployeeBalance($id, $amt, $increse = true) {
    /**
     * It is use for updating employee balance.
     * if increase = true the add given amount from the employee balance.
     * else decrese given amount from the employee balance
     * 
     * @param id employee id
     * @param amt amount to update
     * @param increase true or false
     * 
     * @return nothing
     */

    // Getting connection object.
    $connect = $GLOBALS['connect'];
    // Getting employee balance
    $balance = getEmployeeBalance($id);

    // increase or decrease
    if($increse) {
        $totalBalance = $balance + $amt;
    }
    else {
        $totalBalance = $balance - $amt;
    }
    // Query for update balance
    $query = "UPDATE employee_balance SET balance=$totalBalance WHERE emp_id=$id";
    // Executing the query
    $result = mysqli_query($connect, $query) or die(mysqli_error($connect));
}

function getEmployeeBalance($id) {
    /**
     * It is use for getting employee balance.
     * 
     * @param id employee id
     * 
     * @return balance employee balance
     */

    // Getting connection object.
    $connect = $GLOBALS['connect'];
    // Query for selecting employee balance record of given id
    $query = " SELECT balance FROM employee_balance WHERE emp_id = $id ";
    // Executing the query and store result in $result variable.
    $result = mysqli_query($connect, $query) or die(mysqli_error($connect));
    // Fetching data to $row
    $row = mysqli_fetch_array($result);
    // Return the employee balance.
    return $row['balance'];
}

function createMoneyTransaction($id, $amt, $extra = false) {
    /**
     * It is use for inserting record to money_transaction table.
     * 
     * @param id employee id
     * @param amt transaction amount.
     * @param extra true or false
     * 
     * @return nothing
     */

    // Setting timezone
    date_default_timezone_set('Asia/Kolkata');
    // Getting current date.
    $date = new DateTime();
    $time = $date->format('Y-m-d H:i:s');

    // Getting connection object.
    $connect = $GLOBALS['connect'];
    
    // if extra == true insert transaction with extra='y'
    if($extra) {
        $query = "INSERT INTO money_transaction(emp_id, amount, time, extra) VALUES ($id, $amt, '$time','y')";
    }
    else {
        $query = "INSERT INTO money_transaction(emp_id, amount, time) VALUES ($id, $amt, '$time')";
    }
    // Executing the query.
    $result = mysqli_query($connect, $query) or die(mysqli_error($connect));
}

function deleteMoneyTransaction($id) {
    /**
     * It is use for deleting record from money_transaction table.
     * 
     * @param id transaction id
     * 
     * @return nothing
     */

    // Getting connection object.
    $connect = $GLOBALS['connect'];
    // Query for deleting money_transaction of given id.
    $query = "DELETE FROM money_transaction WHERE id=$id";
    // Executing the query.
    $result = mysqli_query($connect, $query);
}

function getEmployeeName($id) {
    /**
     * It is use for deleting record from money_transaction table.
     * 
     * @param id employee id
     * 
     * @return name employee name
     */

    // Getting connection object.
    $connect = $GLOBALS['connect'];
    // Query for getting employee name of given id from employee table.
    $query = "SELECT ename FROM employee WHERE id = $id";
    // Executing the query.
    $result = mysqli_query($connect, $query) or die(mysqli_error($connect));
    // Fetching the data.
    $row = mysqli_fetch_array($result);
    // Return the name of employee.
    return $row['ename'];
}
?>