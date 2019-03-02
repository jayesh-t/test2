/**
 * This function is use for paying salary or paying extra money in salary.
 * Getting data from table which is present in salary.php and send to the pay-salary.php.
 * This function calls when user click on Paid button of salary table in salary.php
 * 
 * @param button - DOM button element
 */
function paySalary(button) {
    // Getting id of element
    let id = button.getAttribute('id');
    // Getting td of table
    let mainBalanceTd = document.getElementById('main-balance'+id);
    let mainBalance = parseInt(mainBalanceTd.innerHTML);

    // if givenRupees <= 0 then show alert invalid data
    if(mainBalance <= 0) {
        document.getElementById('alert-body').innerHTML = 'Something goes wrong.';
        $('#myAlert').modal('show');
        hideWaitingModal();
    }
    else {
        // Getting button container td
        buttonColumnTd = document.getElementById('td-btn'+id)
        //button.remove();
        // Creating a spinner for waiting response
        buttonColumnTd.innerHTML = '<div class="fa-3x"><font size="5"><i class="fas fa-circle-notch fa-spin"></i></font></div>';

        var xhttp = new XMLHttpRequest();
        // Store extra rupees
        extra = 0;
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // Setting balance to the balance td
                mainBalanceTd.innerHTML = "0";
                // Setting transaction info to last td
                buttonColumnTd.innerHTML = 'Salary paid rupees '+mainBalance;
            } 
        }; 
        // if givenRupees > mainBalance send amt=mainBalance othrwise send givenRupees
        xhttp.open("GET", './data/salary/pay?emp-id='+id+'&payable-amount='+mainBalance, true);
        xhttp.send();
    }
}

/**
 * This function is use for paying extra money to user.
 * This function calls when user click on the pay button of extra-paid form in extra-paid.html
 */
function payExtra() {
    // Showing waiting modal
    showWaitingModal();

    // Getting form data
    let id = document.getElementById('inputContact').value;
    let amount = document.getElementById('inputSal').value;
    name = document.getElementById('inputName').value;

    // Validating form data
    if(id == '' || id == ' ' || name == '') {
        document.getElementById('alert-body').innerHTML = 'Please enter valid ID.';
        $('#myAlert').modal('show');
        hideWaitingModal();
    }
    else if(amount == '' || parseFloat(amount) <= 0 || isNaN(amount)) {
        document.getElementById('alert-body').innerHTML = 'Please enter valid Amount.';
        $('#myAlert').modal('show');
        hideWaitingModal();
    }
    else {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // Showing alert
                document.getElementById('alert-body').innerHTML = amount+' rupees paid to '+name;
                $('#myAlert').modal('show');
                // Showing extra paid
                showExtraPaid();
                // hiding waiting modal
                hideWaitingModal();
            } 
        };
        xhttp.open("GET", './data/salary/pay-extra?emp-id='+id+'&payable-amount='+amount, true);
        xhttp.send();
    }
}