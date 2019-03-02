<?php
/**
 * @USE :
 * This file is use send updated employee record.
 * This file show employee details.
 * This file is use to send request for deleting employee record.
 * 
 * @Require :
 * connection.php file
 * employee id must comes from $_GET
 * 
 * @Process :
 * Getting employee id from $_GET
 * Creating query for getting employee details of given id from employee table.
 * Executing the query and store result in $result variable.
 * Fetching the employee details.
 * Setting employee details to the form. 
 */

// include the connection.php file
include '../../connection.php';

$id = $_GET['id'];
$query = "SELECT * FROM employee WHERE id='$id' ";
$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
$row = mysqli_fetch_array($result);
$name = $row['ename'];
$city = $row['city'];
$number = $row['number'];
$salary = $row['salary'];

?>

<!-- Delete alert Modal -->
<div class="modal fade" id="deleteAlert" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel1">Updated</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are You Sure! You want to delete employee Record.
            </div>
            <div class="modal-footer">
                <a href="#" onclick="deleteEmployee('<?php echo $id; ?>')" class="btn btn-danger" data-dismiss="modal">Delete</a>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>
    <!--End Modal -->
<h3>Update Employee Information</h3>
<form method="POST" class="emp-form pl-2 pr-2 pb-2 pt-2">
    <input type="text" value="<?php echo $id ?>" id="inputId" name="id" hidden>
    <div class="form-group">
        <label for="inputName">Name</label>
        <input type="text" pattern="[a-zA-Z][a-zA-Z ]+" value="<?php echo $name ?>" class="form-control" id="inputName" name="ename" placeholder="Enter Name" require>
    </div>
    <div class="form-group">
        <label for="inputCity">City</label>
        <input type="text" pattern="[a-zA-Z][a-zA-Z ]+" value="<?php echo $city ?>" class="form-control" id="inputCity" name="city" placeholder="City" require>
    </div>
    <div class="form-group">
        <label for="inputContact">Contact Number</label>
        <input type="text" pattern="[789][0-9]{9}" value="<?php echo $number ?>" class="form-control" id="inputContact" name="number" placeholder="Mobile" require>
    </div>
    <div class="form-group">
        <label for="inputSal">Salary</label>
        <input type="number" min="1" value="<?php echo $salary ?>" class="form-control" id="inputSal" name="salary" placeholder="Salary">
    </div>
    <div class="form-group">
        <input type="submit" onclick="updateEmployee()" class="btn btn-primary" value="Update">
        <a href="#" onclick="showEmployee()" type="button" class="btn btn-secondary">Cancel</a>
        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteAlert" >Delete</button>
    </div>
</form>
