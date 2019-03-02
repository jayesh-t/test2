<?php
/**
 * @USE :
 * This file is use to make connection to the database.
 * 
 * @Require :
 * validate-user.php file.
 * 
 */

// If request comes from login.php do not validate user beacause validation is happen in login.php file.
if(basename($_SERVER['SCRIPT_NAME']) != "login.php")
    require dirname(__FILE__).'/validate-user.php';

$host = 'localhost';
$username = 'root';
$password = '';
$dbname = 'sarvesh_construction';
$connect = mysqli_connect($host, $username, $password, $dbname);
?>