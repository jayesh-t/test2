<?php
session_start();
// If request comes with Post method and $_POST['signin'] index is set then follwing code is executed.
if(isset($_POST['signin'])) {
    //include connection.php file.
    include './lib/connection.php';
    //Getting email address
    $email = $_POST['email'];
    //Getting password 
    $password = $_POST['password'];
    //Query for getting data from users table 
    $query = "SELECT * FROM users WHERE email='$email' AND password='$password' ";
    //Executing the query and store result in $result
    $result = mysqli_query($connect, $query);
    //Get how many records are selected
    $numRows = mysqli_num_rows($result);
    //if numRows > 0 that means email and password is correct
    if($numRows) {
        //Fetching the data from $result
        $row = mysqli_fetch_array($result);
        //Setting SESSION variable for tracking or validating the user.
        $_SESSION['userid'] = $row['userid'];
        //Redirect user to index.php file.
        header('location: ./index.php');
    }
}
?>


<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Sign In</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <!-- Login css file -->
    <link rel="stylesheet" href="./css/login.css">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
  </head>
  <body class="text-center">
    <!-- Login Form -->
    <form class="form-signin" action="login.php" method="POST">
      <img class="mb-4" src="./images/login.png" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

      <?php
      //If request comes with Post method and $_POST['signin'] index is set then follwing code is executed.
      if(isset($_POST['signin'])) {
        //if numRows == 0 that means email and password is wrong
        if($numRows == 0) {
      ?>
          <!-- When email and password is wrong then this alert is show to user -->
          <!-- Alert -->
          <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <strong>Something Wrong!</strong> Please enter correct data.
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <!-- End Alert -->
      <?php
        }
      }
      ?>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit" name="signin">Sign in</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
    <!-- End Login Form-->
  </body>
</html>
