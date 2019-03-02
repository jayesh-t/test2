<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.jayeshtajane.salarymanager.modal.MyConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%

System.out.println("REQUEST : FILE = update-emp.jsp");
String empId = request.getParameter("emp-id");
String empName = "";
String empCity = "";
String empContact = "";
String empSal = "";

Connection con = MyConnection.getConnection();
Statement stmt;
stmt = con.createStatement();
String query = "select * from employee where empid='" + empId + "'";
ResultSet rs = stmt.executeQuery(query);

while(rs.next()) {
	empName = rs.getString("ename");
	empCity = rs.getString("city");
	empContact = rs.getString("contact");
	empSal = rs.getString("salary");
}
%>
<!-- Delete alert Modal -->
<div class="modal fade" id="deleteAlert" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel1">Warning</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are You Sure! You want to delete employee Record.
            </div>
            <div class="modal-footer">
                <a href="#" onclick="deleteEmployee(<%out.println(empId); %>)" class="btn btn-danger" data-dismiss="modal">Delete</a>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>
    <!--End Modal -->
<h3>Update Employee Information</h3>
<form method="POST" class="emp-form pl-2 pr-2 pb-2 pt-2">
    <input type="text" value="<%out.println(empId); %>" id="inputId" name="emp-id" hidden>
    <div class="form-group">
        <label for="inputName">Name</label>
        <input type="text" pattern="[a-zA-Z][a-zA-Z ]+" value="<%out.println(empName); %>" class="form-control" id="inputName" name="ename" placeholder="Enter Name" require>
    </div>
    <div class="form-group">
        <label for="inputCity">City</label>
        <input type="text" pattern="[a-zA-Z][a-zA-Z ]+" value="<%out.println(empCity); %>" class="form-control" id="inputCity" name="city" placeholder="City" require>
    </div>
    <div class="form-group">
        <label for="inputContact">Contact Number</label>
        <input type="text" pattern="[789][0-9]{9}" value="<%out.println(empContact); %>" class="form-control" id="inputContact" name="number" placeholder="Mobile" require>
    </div>
    <div class="form-group">
        <label for="inputSal">Salary</label>
        <input type="text" value="<%out.println(empSal); %>" class="form-control" id="inputSal" name="salary" placeholder="Salary">
    </div>
    <div class="form-group">
        <input type="submit" onclick="updateEmployee()" class="btn btn-primary" value="Update">
        <a href="#" onclick="showEmployee()" type="button" class="btn btn-secondary">Cancel</a>
        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteAlert" >Delete</button>
    </div>
</form>
