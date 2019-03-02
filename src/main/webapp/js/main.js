/**
 * This funtion is use to show the waiting modal
 */
function showWaitingModal() {
    modal = document.getElementById("waiting-modal");
    modal.style.display = 'flex';
    modal.style.flexDirection = 'column';
    modal.style.justifyContent = 'center';
    modal.style.alignItems = 'center';
}

/**
 * This function is use to hie the waiting modal
 */
function hideWaitingModal() {
    modal = document.getElementById("waiting-modal");
    modal.style.display = 'none';
}

function changeNavLink(link) {
	// Getting the nav links
    atd = document.getElementById('att-menu-item');
    emp = document.getElementById('emp-menu-item');
    sal = document.getElementById('sal-menu-item');
    extPaid = document.getElementById('extra-paid-menu-item');
    let history = document.getElementById('history-menu-item');
    
    // Removing the class attribute of nav links
    atd.removeAttribute('class');
    emp.removeAttribute('class');
    sal.removeAttribute('class');
    extPaid.removeAttribute('class');
    history.removeAttribute('class');

    // Setting the class attribute to nav link
    atd.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2');
    emp.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2');
    sal.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2');
    extPaid.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2');
    history.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2');
    
    if(link == "attendence")
    	atd.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2 active');
    else if(link == "employee") 
    	emp.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2 active');
    else if(link == "salary")
    	sal.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2 active');
    else if(link == "extra-paid")
    	extPaid.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2 active');
    else if(link == "history")
    	history.setAttribute('class', 'menu-item pl-2 pr-2 pt-2 pb-2 active');
}
function getTodaysDate() {
	let today = new Date();
	let dd = today.getDate();
	let mm = today.getMonth() + 1; //January is 0!

	let yyyy = today.getFullYear();
	if (dd < 10) {
	  dd = '0' + dd;
	} 
	if (mm < 10) {
	  mm = '0' + mm;
	} 
	today =  dd + '/' + mm + '/' + yyyy;
	return today;
}