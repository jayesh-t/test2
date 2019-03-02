package com.jayeshtajane.salarymanager;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeshtajane.salarymanager.employee.Employee;
import com.jayeshtajane.salarymanager.history.History;

public class GetData {
	static private ArrayList<Employee> employee = null;
	static private String employeeJson = null;
	
	static private ArrayList<History> history = null;
	static private String historyJson = null;
	
	
	static public void setEmployee(Employee e) {
		GetData.employee.add(e);
		ObjectMapper om = new ObjectMapper();
		try {
			GetData.employeeJson = om.writeValueAsString(GetData.employee);
		} catch (JsonProcessingException excp) {
			excp.printStackTrace();
		}
	}
	static public void setHistory(History e) {
		GetData.history.add(e);
		ObjectMapper om = new ObjectMapper();
		try {
			GetData.historyJson = om.writeValueAsString(GetData.history);
		} catch (JsonProcessingException excp) {
			excp.printStackTrace();
		}
	}
	
	public static String getEmployeeJson() {
		return employeeJson;
	}
	static public String getHistoryJson() {
		return historyJson;
	}
	
	public String getAttendence() {
		if(GetData.employee == null) {
			employee = new ArrayList<Employee>();
			for(int i=0; i<5; i++) {
				Employee e = new Employee();
				e.setEmpId(i);
				e.setEmpName("a"+i);
				e.setEmpSal(Double.valueOf(1000 + i)); 
				e.setPresent(true); 
				e.setEmpBalance(200001 + i);
				GetData.employee.add(e);
			}
			GetData.employeeJson = convertToJson(employee);
		}
		return GetData.employeeJson;
	}

	public String getEmployee() {
		if(GetData.employee == null) {
			employee = new ArrayList<Employee>();
			for(int i=0; i<5; i++) {
				Employee e = new Employee();
				e.setEmpId(i);
				e.setEmpName("a"+i);
				e.setEmpSal(Double.valueOf(1000 + i));
				e.setPresent(true);
				e.setEmpBalance(200001 + i); 
				GetData.employee.add(e);
			}
			GetData.employeeJson = convertToJson(employee);
		}
		return GetData.employeeJson;
	}
	
	public String getEmployeeName(int empId) {
		if(GetData.employee == null) {
			return "JAYESH";
		}
		for(int i = 0; i < employee.size(); i++) {
			Employee e = employee.get(i);
			if(empId == e.getEmpId())
				return e.getEmpName();
		}
		return null;
	}
	
	public String getSalary() {
		if(GetData.employee == null) {
			employee = new ArrayList<Employee>();
			for(int i=0; i<5; i++) {
				Employee e = new Employee();
				e.setEmpId(i);
				e.setEmpName("a"+i);
				e.setEmpSal(Double.valueOf(1000 + i));
				e.setPresent(true); 
				e.setEmpBalance(200001 + i);
				GetData.employee.add(e);
			}
			GetData.employeeJson = convertToJson(employee);
		}
		return GetData.employeeJson;
	}
	public String getHistory() {
		if(GetData.history == null) {
			history = new ArrayList<History>();
			for(int i=0; i<5; i++) {
				History h = new History();
				h.setEmpId(i); 
				h.setEmpName("a"+i);
				h.setEmpSal(Double.valueOf(1000 + i));
				h.setPresent(true); 
				h.setEmpBalance(200001 + i);
				h.setHistoryId(10 + i);
				h.setExtra(true);
				h.setAmount(200 + i); 
				h.setDate("12/12/2018"); 
				h.setTime("10:30 PM"); 
				GetData.history.add(h);
			}
			GetData.historyJson = convertToJson(history);
		}
		return GetData.historyJson;
	}
	
	public String convertToJson(ArrayList obj) {
		String json = null;
		ObjectMapper om = new ObjectMapper();
		try {
			json = om.writeValueAsString(obj);
			//response.getWriter().append(employeeData);
		} catch (JsonProcessingException excp) {
			excp.printStackTrace();
		}
		return json;
	}
}
