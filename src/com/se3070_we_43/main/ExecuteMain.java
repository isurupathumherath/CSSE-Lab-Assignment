package com.se3070_we_43.main;

import com.se3070_we_43.service.EmployeeService;
import com.se3070_we_43.service.EmployeeServiceImpl;
import com.se3070_we_43.util.XSLTransformUtil;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeServiceImpl();
		try {
			XSLTransformUtil.requestTransform();
			employeeService.addEmployee();
			employeeService.getEmployees();
			employeeService.getEmployeeByID("EMP10004");
			employeeService.removeEmployee("EMP10001");
		} catch (Exception e) {
		}

	}
}
