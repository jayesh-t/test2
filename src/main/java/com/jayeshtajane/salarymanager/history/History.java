package com.jayeshtajane.salarymanager.history;

import com.jayeshtajane.salarymanager.employee.Employee;

public class History extends Employee {
	private int historyId;
	private boolean isExtra;
	private double amount;
	private String date;
	private String time;
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public boolean isExtra() {
		return isExtra;
	}
	public void setExtra(boolean isExtra) {
		this.isExtra = isExtra;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
