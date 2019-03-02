package com.jayeshtajane.salarymanager;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jayeshtajane.salarymanager.employee.Employee;
import com.jayeshtajane.salarymanager.modal.attendence.GetAttendence;
import com.jayeshtajane.salarymanager.modal.attendence.SaveAttendence;
import com.jayeshtajane.salarymanager.modal.employee.DeleteEmployee;
import com.jayeshtajane.salarymanager.modal.employee.GetEmployee;
import com.jayeshtajane.salarymanager.modal.employee.SaveEmployee;
import com.jayeshtajane.salarymanager.modal.employee.UpdateEmployee;
import com.jayeshtajane.salarymanager.modal.history.History;
import com.jayeshtajane.salarymanager.modal.salary.GetSalary;
import com.jayeshtajane.salarymanager.modal.salary.Salary;

/**
 * Servlet implementation class Main
 */
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String root;
	private String path;

    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("REQUEST : FILE = Main.java");
		root = request.getContextPath();
		path = request.getPathInfo();
		System.out.println(request.getPathInfo());
		
		String attendenceList = request.getParameter("attendence-list");
		String attendenceDate = request.getParameter("attendence-date");
		System.out.println("INFO : ATTENDENCE LIST = " + attendenceList);
		System.out.println("INFO : ATTENDENCE Date = " + attendenceDate);
		
		int empId = -1;
		if(request.getParameter("emp-id") != null)
			empId = Integer.parseInt(request.getParameter("emp-id"));
		Map m = request.getParameterMap();
		System.out.println("INFO : MAP LEN = " + m.size()); 
		
		//System.out.println("INFO : Main.java - empId = " + empId);
		
		if(path.equals("/attendence")) {
			/*GetData data = new GetData();
			String attendence = data.getAttendence();*/
			response.getWriter().append(new GetAttendence().getAttendence(attendenceDate));
		}
		else if(path.equals("/attendence/save")) {
			new SaveAttendence().save(attendenceList, attendenceDate);
		}
		
		else if(path.equals("/employee")) {
			response.getWriter().append(new GetEmployee().get());
		}
		else if(path.equals("/employee/name")) {
			response.getWriter().append(new GetEmployee().getName(empId));
		}		
		else if(path.equals("/salary")) {
			response.getWriter().append(new GetSalary().get());
		}
		else if(path.equals("/salary/pay")) {
			String amount = request.getParameter("payable-amount");
			String eid = request.getParameter("emp-id");
			Salary sal = new Salary();
			sal.pay(eid, amount);
		}
		else if(path.equals("/salary/pay-extra")) {
			String amount = request.getParameter("payable-amount");
			String eid = request.getParameter("emp-id");
			Salary sal = new Salary();
			sal.payExtra(eid, amount);
		}
		else if(path.equals("/history")) {
			response.getWriter().append(new History().getHistory());
		}
		else if(path.equals("/employee/update")) {
			Employee emp = new Employee();
			emp.setEmpId(empId); 
			emp.setEmpName(request.getParameter("emp-name"));
			emp.setEmpCity(request.getParameter("emp-city"));
			emp.setEmpContact(request.getParameter("emp-contact"));
			emp.setEmpSal(Double.parseDouble(request.getParameter("emp-sal"))); 
			
			System.out.println("INFO : Main.java update if Employee = \n" + emp);
			
			new UpdateEmployee().update(emp);
		}
		else if(path.equals("/employee/save")) {
			Employee emp = new Employee();
			emp.setEmpName(request.getParameter("emp-name"));
			emp.setEmpCity(request.getParameter("emp-city"));
			emp.setEmpContact(request.getParameter("emp-contact"));
			emp.setEmpSal(Double.parseDouble(request.getParameter("emp-sal"))); 
			
			System.out.println("INFO : Main.java save if Employee = \n" + emp);
			
			new SaveEmployee().save(emp);
		}
		else if(path.equals("/employee/delete")) {
			System.out.println("INFO : Main.java update if Employee id = \n" + empId);
			new DeleteEmployee().delete(empId);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
