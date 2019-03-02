var presentEmployee = '';
function isPresent(present, empId) {
	if(present == true) {
		presentEmployee += empId+',';
		return 'checked';
	}
}
/**
 * This function is use to show the attendence table.
 * This is the first function called in index.php
 */
function showAttendence() {
    presentEmployee = '';
    // Showing the waiting the modal
    showWaitingModal();
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            // Getting the container div
			div = document.getElementById('container');

			changeNavLink("attendence");

            // Setting attendence table in container div
            //div.innerHTML += this.responseText;
            console.log("Response = " + this.responseText);
            if(this.responseText == "") {
                hideWaitingModal();
                return;
            }
            let attendence = JSON.parse(this.responseText);
            //let table = document.getElementById("attendence-table");
            //table.innerHTML = attendence;
            
            console.log(attendence);
            
            //let i = 0;
            let top = `
            <div class="body bg-secondary">
            	<div class="heading pl-1 pr-1 pt-1 pb-1 border-bottom">
            		<span>Today's Attendence</span>
            	</div>
			    <!-- Creating the attendence table. -->
			    <table class="table table-hover table-mb-0" id="attendence-table">
			        <thead class="table-success">
			            <tr>
			            <th scope="col">#</th>
			            <th scope="col">Name</th>
			            <th scope="col">Present</th>
			            </tr>
			        </thead>
	            	<tbody class="table-primary">
            `;
            
            let body = '';
            for(let i = 0; i <= attendence.length - 1; i++) {
            	console.log(attendence.length);
            	console.log(i)
            	console.log("for");
            	body += `
	    	            <tr>
	    	            <th scope="row">`+ attendence[i].empId +`</th>
	    	            <td>`+ attendence[i].empName +`</td>
	    	            <td>
	    	                <div class="form-check">
	    	                    <input 
	    	                        class="form-check-input" 
	    	                        type="checkbox" 
	    	                        id="`+ attendence[i].empId +`" 
	    	                        onchange="setAttendence('`+ attendence[i].empId +`')" 
	    	                         ` + isPresent(attendence[i].present, attendence[i].empId) + `
	    	                    >
	    	                    <label class="form-check-label" for="`+ attendence[i].empId +`">Present</label>
	    	                </div>
	    	            </td>
	    	            </tr>
        	     `;
            }
            
            let bottom = `
            		</tbody>
            	</table>
		   </div>
		   
		   <!-- Attendence Form -->
			<div id="attendence-form">
			    <form>
			        <input type="text" id="attendence-date" value="`+ getTodaysDate() +`" name="attendence-date" hidden>
			        <input type="text" name="attendence-list" id="attendence-box" value="`+ presentEmployee +`" hidden>
			        <input type="submit" value="Save Attendence" name="save" class="btn btn-primary" onclick="saveAttendence()">
			    </form>
			</div>
            `;
            div.innerHTML = top+body+bottom;
            // Hiding the waiting modal
            hideWaitingModal();
		} 
	};
	xhttp.open("GET", './data/attendence?attendence-date=' + getTodaysDate(), true);
	xhttp.send();
}

/**
 * This function is use to show the employee table and Add New Employee button
 */
function showEmployee() {
    // Show th waiting modal
    showWaitingModal();
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            // Getting the container div
            div = document.getElementById('container');

            changeNavLink("employee");
            
            employee = JSON.parse(this.responseText);
            console.log(employee);

            let top = `
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
			            <th scope="col">Salary/per day</th>
			            </tr>
			        </thead>
				    <tbody class="table-primary">
            `;
            
            let body = '';
            for(let i = 0; i <= employee.length - 1; i++) {
	            body += `
			            <tr>
			            <th scope="row">`+ employee[i].empId +`</th>
			            <td><a href="#" onclick="showUpdateEmployeeForm('`+ employee[i].empId +`')">`+ employee[i].empName +`</a></td>
			            <td>`+ employee[i].empSal +`</td>
			            </tr>
	            `;
            }
            let bottom = `
            		</tbody>
            	</table>
			</div>`;

            // Setting attendence table in container div
            div.innerHTML = top+body;
            hideWaitingModal();
		} 
	};
	xhttp.open("GET", './data/employee', true);
	xhttp.send();
}

/**
 * This function is use to show the extra pay form.
 */
function showExtraPaid() {
    // Show th waiting modal
    showWaitingModal();
    var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            // Getting the container div
            div = document.getElementById('container');
            
            changeNavLink("extra-paid");

            // Setting attendence table in container div
            div.innerHTML = this.responseText;
            hideWaitingModal();
		} 
	};
	xhttp.open("GET", './extra-paid.html', true);
	xhttp.send();
}

/**
 * This function is use to show the salary table
 */
function showSalary() {
    // Show th waiting modal
    showWaitingModal();
    var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            // Getting the container div
			div = document.getElementById('container');

			changeNavLink("salary");

            let salary = JSON.parse(this.responseText);
            
            let top = `
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
           `;
            
            let body = '';
            for(let i = 0; i <= salary.length - 1; i++) {
            	body += `
	                    <tr>
	                    <th scope="row">`+ salary[i].empId +`</th>
	                    <td>`+ salary[i].empName +`</td>
	                    <td id="main-balance`+ salary[i].empId +`">`+ salary[i].empBalance +`</td>
	                    <td id="td-btn`+ salary[i].empId +`">
                `;
            
                 if(salary[i].empBalance > 0) {
                     body += `
	                        <button id="`+ salary[i].empId +`" onclick="paySalary(this)">Pay</button>
	                     </td>
	                     </tr>
                    `;
                 }
            }
            let bottom = `
	                </tbody>
	            </table>
	        </div>`;

            
            // Setting attendence table in container div
            div.innerHTML = top+body+bottom;
            hideWaitingModal();
		} 
	};
	xhttp.open("GET", './data/salary', true);
	xhttp.send();
}

/**
 * This function is use to show the history
 */
function showHistory() {
    // Show th waiting modal
    showWaitingModal();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Getting the container div
            div = document.getElementById('container');

            changeNavLink("history");
            
            let history = JSON.parse(this.responseText);
            
            let top = `
            <div class="history-container bg-info">
                <div class="heading">
                    <span>History</span>
                </div>
                `;
            
            let body = '';
            for(let i = 0; i <= history.length - 1; i++) {
            body += `
                <div class="history">
                    <div class="history-content">`;
                    if(history[i].extra == true) {
                    	body += `
                        <a href="#" class="text-white">
                    		Extra `+ history[i].amount +` rupees given to `+ history[i].empName +`
                    	</a>
                    	`;
                    }
                    else {
                        body += `
                        <a href="#" class="text-white">
                        	Salary paid rupees `+ history[i].amount +` to `+ history[i].empName +`
                        </a>
                        `;
                        
                        }
                    body += `
                    </div>
                    <div class="history-time text-right"> 
                        <span class="text-warning bold">Date: `+ history[i].date +`</span><br>
                        <span class="text-warning bold">Time: `+ history[i].time +`</span>
                    </div>
                </div>
            `;
            }
            let bottom = "</div>";
            
            // Setting attendence table in container div
            div.innerHTML = top+body+bottom;
            hideWaitingModal();
        } 
    };
    xhttp.open("GET", './data/history', true);
    xhttp.send();
}

/**
 * This function is use to show the employee form.
 */
function showNewEmployeeForm() {
    // Show the waiting modal
    showWaitingModal();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Getting the container div
            div = document.getElementById('container');
            div.innerHTML = this.responseText;

            // Getting nav link 
            emp = document.getElementById('emp-menu-item');
            // Removing the class attribute of nav link
            emp.removeAttribute('class');
            // Adding class attribute to nav link
            emp.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2');
            // Hide waiting modal
            hideWaitingModal();
        } 
    };
    xhttp.open("GET", './add-new.html', true);
    xhttp.send();
}

/**
 * This function is use to show the update form with given employee data
 * 
 * @param id - employee id
 */
function showUpdateEmployeeForm(id) {
	console.log(id);
    // Show the waiting modal
    showWaitingModal();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Getting the container div
            div = document.getElementById('container');
            div.innerHTML = this.responseText;
            
            // Getting nav link 
            emp = document.getElementById('emp-menu-item');
            // Removing the class attribute of nav link
            emp.removeAttribute('class');
            // Adding class attribute to nav link
            emp.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2');
            // Hide waiting modal
            hideWaitingModal();
        } 
    };
    xhttp.open("GET", './update-emp.jsp?emp-id=' + id, true);
    xhttp.send();
}