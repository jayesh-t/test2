/**
 * This function set the attendence to the input tag which id is 'attendence-box'.
 * When user click on the ckeck box of attendence table then this function is called.
 * if user make ckeck is ckecked then this function append the given employee id to the attendence-box else removes the given id from attendence-box.
 * 
 * @param id - employee id
 */
function setAttendence(id) {
    // Get the checkbox
    var checkBox = document.getElementById(id);
    // Get the attendence box
    var attendenceBox = document.getElementById("attendence-box");
  
    // If the checkbox is checked, append id to the attendencebox else removes given id from attendence-box.
    if (checkBox.checked == true){
        attendenceBox.value += id+',';
    } else {
        // Getting values from attendence test box
        str = attendenceBox.value;
        // Set attendence text box to empty.
        attendenceBox.value = '';
        // Make array employee id.
        atarr = str.split(",");
        // Setting employee id's to attendence text box one by one with seperated by comma.
        for(i=0; i<atarr.length; i++) {
            // Check given id is present in that array or not.
            // if it is present do not add to the attendence text box.
            if(atarr[i] == id) {
                continue;
            }
            attendenceBox.value += atarr[i]+',';
        }
    }
}

/**
 * @deprecated - This functionality is alredy define in php
 */
function setRemoveAttendence() {
    previous = new Array();
    update = new Array();
    updateAttendence = document.getElementById('update-attendence-box');
    previousAttendence = document.getElementById('previous-attendence-box');
    atarr = previousAttendence.value.split(",");
    
    for(i=0; i<atarr.length-1; i++) {
        checkBox = document.getElementById(atarr[i]);
        console.log(atarr[i]);
        console.log(checkBox);
        
        if(checkBox.checked == true) {
            previous[i] = atarr[i];
        }
        else {
            update[i] = atarr[i];
        }
    }

    updateAttendence.value = update.toString();
    previousAttendence.value = previous.toString();
}

/**
 * When user click on Save Attendence button of attendence.php file then this function is called.
 * This function send the attendence and today's date to the save-attendence.php file for saving the attendence.
 */
function saveAttendence() {
    event.preventDefault();
    // Show waiting modal
    showWaitingModal();
    // Getting date from input type date which is hidden in attendence.php file
    let attendenceDate = document.getElementById('attendence-date').value;
    // Getting the attendence which is hidden in attendence.php file
    let attendence = document.getElementById('attendence-box').value;
    console.log("Attendence = " + attendence);
    let dataString = 'attendence-list='+attendence+'&attendence-date='+attendenceDate;
    console.log("attendence data = " + dataString);

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Setting the massage to the body of alert which is present in my-alert.html
            document.getElementById('alert-body').innerHTML = 'Attendence Successfully Saved.';
            // Showing the alert.
            $('#myAlert').modal('show');
            console.log(this.responseText);
            // Hiding the waiting modal
            hideWaitingModal();
        } 
    };
    xhttp.open("POST", './data/attendence/save', true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(dataString);
}