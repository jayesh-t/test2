/**
 * This function send given employee id to the server and set the response to the given input tag.
 * 
 * @param id - employee id
 * @param inputTag - input tag element
 */
function getUserName(id, inputTag) {
    console.log(id);
    if(id != '') {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);
                inputTag.value = this.responseText;
            } 
        };
        xhttp.open("GET", './data/employee/name?emp-id='+id, true);
        xhttp.send();
    }
    else {
        inputTag.value = '';
    }
}

function validateForm(name, city, contact, salary) {
    if(name.trim().length >= 1) {
        if(city.trim().length >= 1) {
            if(contact.trim().length == 10) {
                if(salary.trim().length > 0) {
                    salary = parseFloat(salary);
                    if(salary > 0) {
                        return true;
                    }
                    document.getElementById('alert-body').innerHTML = 'Salary should be > 0';
                    $('#myAlert').modal('show');
                    hideWaitingModal();
                    return false;
                }
                else {
                    document.getElementById('alert-body').innerHTML = 'Please enter valid salary';
                    $('#myAlert').modal('show');
                    hideWaitingModal();
                    return false;
                }
            }
            else {
                document.getElementById('alert-body').innerHTML = 'Please enter valid contact number';
                $('#myAlert').modal('show');
                hideWaitingModal();
                return false;
            }
        }
        else {
            document.getElementById('alert-body').innerHTML = 'Please enter valid city';
            $('#myAlert').modal('show');
            hideWaitingModal();
            return false;
        }
    }
    else {
        document.getElementById('alert-body').innerHTML = 'Please enter valid name';
        $('#myAlert').modal('show');
        hideWaitingModal();
        return false;
    }
}

/**
 * This function call when user click on the Add button of employee form which is present in add-new.html file.
 * Getting the employee data from the employee form and send these data to the add-new.php file for saving the data.
 */
function addNewEmployee() {
    event.preventDefault();
    // Showing the waiting modal
    showWaitingModal();
    // Getting employee data from the employee form.
    let name = document.getElementById('inputName').value;
    let city = document.getElementById('inputCity').value;
    let contact = document.getElementById('inputContact').value;
    let salary = document.getElementById('inputSal').value;

    if(validateForm(name, city, contact, salary)) {

        // Creating data string for sending to the server
        dataString = 'emp-name='+name+'&emp-city='+city+'&emp-contact='+contact+'&emp-sal='+salary;
    
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);
                if(this.responseText == " ok") {
                    // Hiding the waiting modal
                    hideWaitingModal();
                    // Displaying the employee's
                    showEmployee();
                }
                else {
                    // Displaying th alert
                    document.getElementById('alert-body').innerHTML = 'Something Wrong! Please try again.';
                    $('#myAlert').modal('show');
                    hideWaitingModal();
                }
            } 
        };
        xhttp.open("POST", "./data/employee/save", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(dataString);

    }
}

/**
 * This function call when user click on the Update button of update employee form which is present in update-emp.php file.
 * Getting the employee data from the update employee form and send these data to the update.php file for updating the data.
 */
function updateEmployee() {
    event.preventDefault();
    // Showing waiting modal
    showWaitingModal();
    // Getting employee data from the update employee form. 
    let id = document.getElementById('inputId').value;
    let name = document.getElementById('inputName').value;
    let city = document.getElementById('inputCity').value;
    let contact = document.getElementById('inputContact').value;
    let salary = document.getElementById('inputSal').value;

    console.log("emp-id = " + id);
    // Creating data string for sending to the server


    if(validateForm(name, city, contact, salary)) {
        dataString = 'emp-id='+id+'&emp-name='+name+'&emp-city='+city+'&emp-contact='+contact+'&emp-sal='+salary;
        console.log("data = " + dataString);
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseText == " ok") {
                    // Hide the waiting modal
                    hideWaitingModal();
                    // Displaying the employee's
                    showEmployee();
                }
                else {
                    // Displaying the alert
                    document.getElementById('alert-body').innerHTML = 'Something Wrong! Please try again.';
                    hideWaitingModal();
                }
            } 
        };
        xhttp.open("POST", "./data/employee/update", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(dataString);
    }
}

/**
 * This function call when user click on the Delete button of update employee form which is present in update-emp.php file.
 * Send given employee id to the delete-employee.php file for deleting the employee.
 * 
 * @param id - employee id
 */
function deleteEmployee(id) {
    event.preventDefault();
    showWaitingModal();

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText == " ok") {
                console.log('rst = '+ this.responseText);
                hideWaitingModal();
                showEmployee();
            }
            else {
                document.getElementById('alert-body').innerHTML = 'Something Wrong! Please try again.';
                hideWaitingModal();
            }
        } 
    };
    xhttp.open("GET", "./data/employee/delete?emp-id=" + id, true);
    xhttp.send();
}