<?php
/**
 * @USE :
 * This file is use for showing all money transaction.
 * 
 * @Require :
 * connection.php file
 * employee-info.php file -- This file use some function of employee-info.php file
 *  
 * @Output :
 * It printing the whole html structure of tranasction history.
 * 
 * @Process :
 * Getting the all money tranasction from database in descending order of time.
 * 
 * Finally deleting the money transaction.
 */
include '../../connection.php';
include '../../employee-info.php';

// Query for getting all money transaction from database.
$query = "SELECT * FROM money_transaction ORDER BY time desc";
// Executing the query.
$result = mysqli_query($connect, $query);
?>

<!-- History Container -->
<div class="history-container bg-info">
    <!-- Heading -->
    <div class="heading">
        <span>History</span>
    </div>
    <?php
    /**
     * Retriving the data from resultset.
     * Creating div element with class "history" for every row.
     * In the div creating two div. One div with class "history-content" and another div with class "history-time".
     * 
     * In "history-content" div setting transaction massage using the column name 'extra' of database.
     * If $row['extra'] == 'y' that means employee getting extra money from manager.
     * If $row['extra'] == 'n' that means in this tranasction manager paid salary to employee.
     * 
     * In "history-time" div setting "tranasaction time" and "cancel tranasction button".
     * In cancel button add a canelTranasction('id') on "onclick" event.
     */
    while($row = mysqli_fetch_array($result)) {
    ?>
    <div class="history">
        <div class="history-content">
            <?php 
            if($row['extra'] == 'y') {
            ?>
                <a href="#" class="text-warning">
                    Extra <?php echo $row['amount']; ?> rupees given to <?php echo getEmployeeName($row['emp_id']) ?>.
                </a>
            <?php
            }
            else {
            ?>
                <a href="#" class="text-warning">
                    Salary paid rupees <?php echo $row['amount']; ?> to <?php echo getEmployeeName($row['emp_id']) ?>.
                </a>
            <?php
            }
            ?>
        </div>
        <div class="history-time text-right">
            <button class="btn btn-danger btn-sm" onclick="cancelTransaction('<?php echo $row['id']; ?>')">Cancel</button><br>
            <?php 
                echo '<span class="text-dark bold">Date: '.$row['time'].'</span>';
            ?>
        </div>
    </div>
    <?php
    }
    /**
     * After loop "history" div looks like
     * <div class="history">
     *   <div class="history-content">
     *     **If extra == y**
     *     <a href="#" class="text-warning"> Extra "amount" ruppes given to "employee name"</a>
     *     **else**
     *     <a href="#" class="text-warning"> Salary paid rupees "amount" to "employee name"</a>
     *   </div>
     *   <div class="history-time text-right">
     *     <button class="btn btn-danger btn-sm" onclick="cancelTransaction('tranascation id')">Cancel</button><br>
     *     <span class="text-dark bold">Date: 2019-01-04 21:03:37</span>'.
     *   <div>
     * </div>
     * for every record creating new "history" class div.
     */
    ?>
</div>