package com.nexstream.exercise.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexstream.domains.BaseResp;
import com.nexstream.domains.Employee;
import com.nexstream.domains.EmployeeList;
import com.nexstream.domains.ErrorResp;
import com.nexstream.helloworld.entity.Test;
import com.nexstream.helloworld.service.TestService;

@Controller
public class RestSample {
	@Resource
	private TestService testService;
	
	@RequestMapping(value="/getEmployee", method=RequestMethod.GET)
	@ResponseBody
	public Employee getEmployee(){
		Employee employee = new Employee();
		employee.setEmpName("David Chong");
		employee.setEmpNo("001");
		
		return employee;
	}
	
	@RequestMapping(value="/getEmployeeList", method=RequestMethod.GET)
	@ResponseBody
	public EmployeeList getEmployeeList(){
		Employee employee1 = new Employee();
		employee1.setEmpName("David Chong");
		employee1.setEmpNo("001");
		
		Employee employee2 = new Employee();
		employee2.setEmpName("Eve Tan");
		employee2.setEmpNo("002");
		
		Employee employee3 = new Employee();
		employee3.setEmpName("West Chin");
		employee3.setEmpNo("003");
		
		List<Employee> employeeArray = new ArrayList<Employee>();
		employeeArray.add(employee1);
		employeeArray.add(employee2);
		employeeArray.add(employee3);
		
		EmployeeList employeeList= new EmployeeList();
		employeeList.setEmployeeList(employeeArray);
		return employeeList;
	}
	
	@RequestMapping(value="/getTests", method=RequestMethod.GET)
	@ResponseBody
	public List<Test> geTests()throws Exception{
		return testService.geTests();
	}
	
	@RequestMapping(value="/getTest/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Test geTestById(@PathVariable Long id)throws Exception{
		return testService.getTest(id);
	}
	
	@RequestMapping(value="/saveNewTest", method=RequestMethod.POST)
	@ResponseBody
	public Object saveTest(@RequestBody Test test)throws Exception{
		String commentField = test.getComments();
		if (commentField==null) commentField="";
		
//		if (commentField.equalsIgnoreCase("")){
//			ErrorResp errorResp = new ErrorResp();
//			errorResp.setCode("500");
//			errorResp.setMessage("Comment field is require!");
//			
//			return errorResp;
//		}
//		
		testService.saveOrUpdate(test);
		BaseResp resp = new BaseResp();
		resp.setCode("200");
		resp.setMessage("New Test has been added successfully");
		return resp;
	}
	
	@RequestMapping(value="/updateTest/{id}", method=RequestMethod.POST)
	@ResponseBody
	public BaseResp saveTest(@PathVariable Long id, @RequestBody Test test)throws Exception{
		test.setId(id);
		testService.saveOrUpdate(test);
		BaseResp resp = new BaseResp();
		resp.setCode("200");
		resp.setMessage("New Test has been update successfully");
		return resp;
	}
	
	@RequestMapping(value="/saveAndUpdate", method=RequestMethod.POST)
	@ResponseBody
	public BaseResp saveAndUpdateTest(@RequestBody Test test)throws Exception{
		if(test.getComments() == null || test.getComments().isEmpty() ) {
			ErrorResp error = new ErrorResp();
			error.setCode("500");
			error.setMessage("Comment field is empty.");
			return error;
		}
		
		testService.saveOrUpdate(test);
		
		BaseResp resp = new BaseResp();
		resp.setCode("200");
		if(test.getId() == null) resp.setMessage("New Test has been added successfully");
		else resp.setMessage("New Test has been updated successfully");
		return resp;
	}
	
	@RequestMapping(value="/deleteTest/{id}", method=RequestMethod.POST)
	@ResponseBody
	public BaseResp deleteSingleTest(@PathVariable Long id, @RequestBody Test test)throws Exception{
		testService.delete(id);
		BaseResp resp = new BaseResp();
		resp.setCode("200");
		resp.setMessage("Test with id: " + id + " has been delete successfully." );
		return resp;
	}

	
	//Task1 : Combile saveNewTest & update test api + validation
	//Task2 : Delete api
	
	//Task3: Test table 
	  //Add additional two fields (Integer, Double)
	

}
