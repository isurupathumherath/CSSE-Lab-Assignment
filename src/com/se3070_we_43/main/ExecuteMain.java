package com.se3070_we_43.main;

import com.se3070_we_43.service.EmployeeService;
import com.se3070_we_43.service.EmployeeServiceImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecuteMain {

    /**
     * Initialize logger.
     */
    public static final Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        try {
            employeeService.createEmployeeTable();
            employeeService.dataReaderXML();
            employeeService.addEmployee();
            employeeService.getEmployees();
            employeeService.getEmployeeByID("EMP10004");
            employeeService.removeEmployee("EMP10001");
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
