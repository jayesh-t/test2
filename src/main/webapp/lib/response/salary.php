<?php
/**
 * @USE :
 * This file is use to show employee salary.
 * 
 * @Require :
 * connection.php file
 * 
 * @Output :
 * Prints the employee id, name, and balance in table format.
 * 
 * @Process :
 * *** For currently working ****
 * Query for selecting employee who are working.
 * Executing the query and storing the result to $result.
 * Fetching employee record one by one.
 * Query for getting employee balance.
 * Executing the query and storing the result to $balance.
 * Fetching balance from $balance to $balance.
 * Setting employee data to the table.
 * Setting balance to the table.
 * Setting input box and button to the table. Set dafault value of input box to amout
 * Set button attribute empBal = employee balance.
 * Set paySalary(this) function to onclick event of paid button.
 * 
 * *** For not working ***
 * Query for selection who are working.
 * Executing the query and storing the result to $result.
 * next process same.
 */

include '../connection.php';

// For those who is working
$query = "SELECT * FROM employee WHERE is_working='y' ";
$result = mysqli_query($connect, $query) or die(mysqli_error($connect)); 
?>
<div class="body bg-secondary">
    <div class="heading pl-1 pr-1 pt-1 pb-1 border-bottom"><span>Working Employee Balance</span></div>
        <table class="table table-hover">
            <thead class="table-success">
                <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Balance</th>
                </tr>
            </thead>
        <tbody class="table-primary">
        <?php
        while($row = mysqli_fetch_array($result)) {
            $id = $row['id'];
            $name = $row['ename'];
            $query = "SELECT balance FROM employee_balance WHERE emp_id = $id ";
            $balResult = mysqli_query($connect, $query) or die(mysqli_error($connect));
            $balance = mysqli_fetch_array($balResult);
            $balance = $balance['balance'];
        ?>
            <tr>
            <th scope="row"><?php echo $id ?></th>
            <td><?php echo $name ?></td>
            <td id="main-balance<?php echo $id ?>"><?php echo $balance ?></td>
            <td id="td-btn<?php echo $id ?>">
        <?php
            if($balance > 0)
                echo '<input type="number" id="input-balance'.$id.'" class="input-sm" value="'.$balance.'">
                <button id="'.$id.'" onclick="paySalary(this)">Paid</button>';
            echo '</td>
            </tr>';
        }
        ?>
        </tbody>
    </table>
</div>

<?php
// For those who is not working
$query = "SELECT * FROM employee WHERE is_working='n' ";
$result = mysqli_query($connect, $query) or die(mysqli_error($connect)); 
?>

<div class="body bg-secondary">
    <div class="heading pl-1 pr-1 pt-1 pb-1 border-bottom"><span>Other Employee Balance</span></div>
        <table class="table table-hover">
            <thead class="table-success">
                <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Balance</th>
                </tr>
            </thead>
            <tbody class="table-primary">
        <?php
            while($row = mysqli_fetch_array($result)) {
                $id = $row['id'];
                $name = $row['ename'];
                $query = "SELECT balance FROM employee_balance WHERE emp_id = $id ";
                $balResult = mysqli_query($connect, $query) or die(mysqli_error($connect));
                $balance = mysqli_fetch_array($balResult);
                $balance = $balance['balance'];
            ?>
                <tr>
                <th scope="row"><?php echo $id ?></th>
                <td><?php echo $name ?></td>
                <td id="main-balance<?php echo $id ?>"><?php echo $balance ?></td>
                <td id="td-btn<?php echo $id ?>">
            <?php
                if($balance > 0)
                    echo '<input type="number" min="1" class="input-sm" id="input-balance'.$id.'" value="'.$balance.'">
                    <button id="'.$id.'" empBal="'.$balance.'"  onclick="paySalary(this)">Paid</button>';
            echo '</td>
            </tr>';
        }
        ?>
            </tbody>
        </table>
    </div>
</div>
