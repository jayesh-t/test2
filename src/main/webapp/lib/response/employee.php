<?php
/**
 * @USE :
 * This file is use for Showing employee who are curently working.
 * 
 * @Require :
 * connection.php file
 * 
 * @Output :
 * It Show the Add new employee button.
 * It show employee record in table format.
 * 
 * @Process :
 * Creating query for selecting employee record who is currently working.
 * Executing the query and store result in $result variable.
 * Fetching data one by one from $result to $row.
 * Setting employee data to the table.
 */

include '../connection.php';

$query = "SELECT * FROM employee Where is_working='y' ";
$result = mysqli_query($connect, $query);

?>
<!-- Add New Employee Button -->
<main class="employee">
  <div class="row mt-3 mb-3">
      <a href="#" onclick="showNewEmployeeForm()" class="btn btn-primary btn-lg">Add New Employee</a>
  </div>
</main>

<!-- Employee Table -->
<div class="body bg-secondary">
    <table class="table table-hover table-mb-0">
        <thead class="table-success">
            <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Salary</th>
            </tr>
        </thead>
        <tbody class="table-primary">
            <?php
            // Fetching data one by one
            while($row = mysqli_fetch_array($result)) {
                // Setting data to the table
            ?>
            <tr>
            <th scope="row"><?php echo $row['id'] ?></th>
            <td><a href="#" onclick="showUpdateEmployeeForm('<?php echo $row['id'] ?>')"><?php echo $row['ename'] ?></a></td>
            <td><?php echo $row['salary'] ?></td>
            </tr>
            <?php
            }
            ?>
        </tbody>
    </table>
</div>
<!-- Employee Table -->
