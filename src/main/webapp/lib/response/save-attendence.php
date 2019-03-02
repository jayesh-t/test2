<?php
/**
 * @USE :
 * This file is use to save or update the attendence of current date.
 * 
 * @Require :
 * connection.php file
 * employee-info.php file
 * date must comes from $_GET
 * attendence must comes from $_GET
 * 
 * @Output :
 * 
 * @Process :
 * Get the date and attendence from $_GET
 * creating attendence_list string.
 * converting attendence_list to an array newAttendence.
 * Query for getting attendence of current date.
 * Executing query and store result in $result.
 * Fetching data from $result to $row and fetching attendence from $row['attendence] to $attendence.
 * Creating oldAttendence array from $attendence.
 * if(mysqli_num_rows($result > 0)) today's entry already inserted in table. we have only update the attendende.
 * then Query for update attendnece and execute the query. And call the function updateBalance(). 
 * else Query for insert attendence and execute the query. And call the function updateBalance().
 */

include '../connection.php';
include '../employee-info.php';

$date = $_GET['date'];
$attendence = $_GET['attendence'];

$arr = explode(",",$attendence);
$attendence_list = '';
for($i=0; $i<sizeof($arr); $i++) {
    if($arr[$i] != null) {
        $attendence_list .= $arr[$i].',';
    }
}
$newAttendence = $arr = explode(",",$attendence_list);;
$query = "SELECT * FROM attendence WHERE date='$date' ";
$result = mysqli_query($connect, $query) or die(mysqli_error($connect)); 
$row = mysqli_fetch_array($result);
$attendence = $row['attendence'];
$oldAttendence = explode(",",$attendence);

if(mysqli_num_rows($result)) {
    $query = "UPDATE attendence SET attendence='$attendence_list' WHERE date='$date' ";
    $result = mysqli_query($connect, $query) or die(mysqli_error($connect));

    updateBalance($newAttendence, $oldAttendence);
}
else {
    $query = "INSERT INTO attendence VALUES ('$date', '$attendence_list')";
    $result = mysqli_query($connect, $query) or die(mysqli_error($connect));

    for($i = 0; $i<sizeof($newAttendence)-1; $i++) {
        $salary = getEmployeeSalary($newAttendence[$i]);
        updateEmployeeBalance($newAttendence[$i], $salary);
    }
}

function updateBalance($newAttendence, $oldAttendence) {
    /**
     * This loop is responsible to Check if employee balace is already updated or not.
     * If emp-id of new-attendence is present in old-attendence that means balance is already updated.
     * If emp-id of new-attendence is not present in old attendence then update the balance of employee.
     */
    for($i=0; $i<sizeof($newAttendence); $i++) {
        $isPresent = false;
        $id = $newAttendence[$i];

        for($j=0; $j<sizeof($oldAttendence); $j++) {
            if($newAttendence[$i] == $oldAttendence[$j]) {
                $isPresent = true;
                break;
            }
        }
        if(! $isPresent) {
            $salary = getEmployeeSalary($id);
            updateEmployeeBalance($id, $salary);
        }
    }

    /**
     * This loop is responsible to check if emp balance is by mistake update or not.
     * If emp-id of old-attendence is present not in new-attendence that means balance is by mistake updared.
     * Then reduce the balance.
     */
    for($i=0; $i<sizeof($oldAttendence); $i++) {
        $isPresent = false;
        $id = $oldAttendence[$i];

        for($j=0; $j<sizeof($newAttendence); $j++) {
            if($oldAttendence[$i] == $newAttendence[$j]) {
                $isPresent = true;
                break;
            }
        }
        if(! $isPresent) {
            $salary = getEmployeeSalary($id);
            updateEmployeeBalance($id, $salary, false);
        }
    }
}
?>