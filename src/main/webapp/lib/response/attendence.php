<?php
/**
 * @USE :
 * This file is use for Showing attendence or sending attendence to server of current date.
 * If employee is present make ckeckbox ckecked else unchecked.
 * After that Click on save attendence.
 * 
 * @Require :
 * connection.php file
 * 
 * @Output :
 * Showing the attendence table
 * Setting on attendence form which is hidden form
 * 
 * @Process :
 * Setting default timezone and getting current date in $date variable
 * Getting the all employee record which currently is working
 * Getting the attendence of current date.
 * attendence table attendence column contain employee number who are present today [attendence column contain comma seperated values like 1,5,7,2]
 * Creating attendence_list array of attendence column data.
 * Getting one by one data of employee
 * Setting data to particular column.
 * In last column there is checkbox.
 * If employee is present today then this check box make checked by default otherwise unchecked.
 * To know the employee is present or not call the function isPresent($employee_id, attendence_list)
 * This function return string like 'ckecked' or '' empty string. This string set to checkbox as a attribute.
 * 
 * Every checkbox have a setAttendnece(employee id). 
 * When onchange event occure on checkbox this is function called.
 * 
 */

// include connection.php file
include '../connection.php';

// Setting the default time zone
date_default_timezone_set('Asia/Kolkata');
// Getting current date
$datetime = new DateTime();
$date = $datetime->format('Y-m-d');

// Query for selecting employee record who are currently working in company.
$query = "SELECT * FROM employee WHERE is_working='y' ";
// Executing the query and store result in $result variable.
$result = mysqli_query($connect, $query) or die(mysqli_error($connect));

// Query for selecting attendence record of current data which stores in $date variable.
$query = "SELECT * FROM attendence WHERE date='$date' ";
// Executing the query and storing result in $attendence variable.
$attendence = mysqli_query($connect, $query) or die(mysqli_error($connect));
// Getting the first record from $attendence result set and store in $atstr
$atstr = mysqli_fetch_array($attendence);
// Creating attendence list array
$attendence_list = explode(",",$atstr['attendence']);
?>

<div class="body bg-secondary">
    <div class="heading pl-1 pr-1 pt-1 pb-1 border-bottom">
        <span>Today's Attendence</span>
    </div>
    <!-- Creating the attendence table. -->
    <table class="table table-hover table-mb-0">
        <thead class="table-success">
            <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Present</th>
            </tr>
        </thead>
        <tbody class="table-primary">
            <?php
            // Getting employee record one by one
            while($row = mysqli_fetch_array($result)) {
            ?>
            <tr>
            <!-- Setting employee id in the th -->
            <th scope="row"><?php echo $row['id'] ?></th>
            <!-- Setting employee name in the th -->
            <td><?php echo $row['ename'] ?></td>
            <td>
                <!--
                    * Checking employee is present or not using isPresent() function.
                    * If is present making check box ckecked by default. 
                    * set the setAttendence() function to the onchange event.
                -->
                <div class="form-check">
                    <input 
                        class="form-check-input" 
                        type="checkbox" 
                        id="<?php echo $row['id'] ?>" 
                        onchange="setAttendence('<?php echo $row['id'] ?>')" 
                        <?php echo isPresent($row['id'], $attendence_list) ?>
                    >
                    <label class="form-check-label" for="<?php echo $row['id'] ?>">Present</label>
                </div>
            </td>
            </tr>
            <?php
            }
            /**
             * After loop table looks like follow -
             * #    Name        Present []
             * --------------------------------------
             * 1    Jay         [checked] Present
             * 2    Ganu        [un-checked] Present
             * --------------------------------------
             */
            ?>
        </tbody>
    </table>
    <!-- End Attendence table -->
</div>

<?php
/**
 * @param $id , $attendence_list
 * @return String 
 * 
 * attendence_list contain current attendence who are present today. 
 * In this array if we find employee id that means employee is present today.
 * This function check the given id is present in given attenedence list or not.
 * If it is present then return a 'checked' String else return a '' empty string.
 */
function isPresent($id, $attendence_list) {
    for($i=0; $i<sizeof($attendence_list); $i++) {
        if($attendence_list[$i] == $id) {
            return 'checked';
        }
    }
    return '';
}
?>
<!-- Attendence Form -->
<div id="attendence-form">
    <div>
        <input type="date" id="attendence-date" value="<?php echo $date; ?>" name="date" hidden>
        <input type="text" name="att" id="attendence-box" value="<?php echo $atstr['attendence']; ?>" hidden>
        <input type="submit" value="Save Attendence" name="save" class="btn btn-primary" onclick="saveAttendence()">
    </div>
</div>

