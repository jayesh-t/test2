
<!DOCTYPE html>
<%@page import="java.lang.annotation.Inherited"%>
<%System.out.println("REQUEST : FILE = index.jsp"); %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Construction</title>

    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <!-- Project Style Sheet -->
    <link rel="stylesheet" href="./css/style.css">

    <style>
    
    </style>
    <!-- Project Javascript -->
    <script src="./js/main.js"></script>
    <script src="./js/show.js"></script>
    <script src="./js/attendence.js"></script>
    <script src="./js/employee.js"></script>
    <script src="./js/money.js"></script>
    <!-- JQuery CDN -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.js CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <!-- Bootstrap CDN -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</head>
<body>
<%@include file="./include/waiting-modal.html" %>
<%@include file="./include/my-alert.html" %>

<div class="main">
    <!-- include header file -->
    <%@include file="./include/header.html" %>
    <!-- In this div content added using javascript and these content comes from server. -->
    <div id="container">
    	<!-- When user visit this page first user see the attendence page because of this function -->
    	<script>showAttendence()</script>
    </div>
    <!-- include footer file -->
    <%@include file="./include/footer.html" %>
</div>

</body>
</html>