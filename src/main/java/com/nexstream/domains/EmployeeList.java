package com.nexstream.domains;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
	
	private List<Employee> employeeList = new ArrayList<Employee>(); 

	public List<Employee> getEmployeeList()
	{
		return employeeList;
	}
	
	public void setEmployeeList(List<Employee> employeeList)
	{
		this.employeeList = employeeList;
	}

}
