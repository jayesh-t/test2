package com.jayeshtajane.salarymanager.employee;

public class Employee {
	private int empId;
	private String empName;
	private Double empSal;
	private String empCity;
	private String empContact;
	private boolean isPresent;
	private double empBalance;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getEmpSal() {
		return empSal;
	}
	public String getEmpCity() {
		return empCity;
	}
	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}
	public String getEmpContact() {
		return empContact;
	}
	public void setEmpContact(String empContact) {
		this.empContact = empContact;
	}
	public void setEmpSal(Double empSal) {
		this.empSal = empSal;
	}
	public boolean isPresent() {
		return isPresent;
	}
	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	public double getEmpBalance() {
		return empBalance;
	}
	public void setEmpBalance(double empBalance) {
		this.empBalance = empBalance;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSal=" + empSal + ", empCity=" + empCity
				+ ", empContact=" + empContact + ", isPresent=" + isPresent + ", empBalance=" + empBalance + "]";
	}
	
}
