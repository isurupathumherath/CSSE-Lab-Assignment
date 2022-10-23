package com.se3070_we_43.service;

import java.util.logging.Logger;

public interface EmployeeService {

	/**  Initialize logger. */
	public static final Logger log = Logger.getLogger(EmployeeService.class.getName());
	
	/**
	 * Adds the employee.
	 */
	public void addEmployee();
	
	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	public void getEmployees();
	
	/**
	 * Gets the employee by ID.
	 *
	 * @param id - the id
	 * @return the employee by ID
	 */
	public void getEmployeeByID(String id);
	
	/**
	 * Update employee.
	 *
	 * @param id - the id
	 */
	public void updateEmployee(String id);
	
	/**
	 * Removes the employee.
	 *
	 * @param id - the id
	 */
	public void removeEmployee(String id);
	
}
