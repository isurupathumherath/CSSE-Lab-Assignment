package com.se3070_we_43.service;

public interface EmployeeService {

    /**
     * Adds the employee.
     */
    void addEmployee();

    /**
     * Gets the employees.
     */
    void getEmployees();

    /**
     * Gets the employee by ID.
     *
     * @param id - the id
     */
    void getEmployeeByID(String id);

    /**
     * Removes the employee.
     *
     * @param id - the id
     */
    void removeEmployee(String id);

    /**
     * Create employee table.
     */
    void createEmployeeTable();

    /**
     * Read data from XML file
     */
    void dataReaderXML();
}
