<?php
/**
 * @USE :
 * This file is use to validate the user. 
 */

if(!isset($_SESSION)) { 
    session_start(); 
}

if(! isset($_SESSION['userid'])) {
    header('location: http://localhost/employee-salary-manager/login.php');
}
?> 