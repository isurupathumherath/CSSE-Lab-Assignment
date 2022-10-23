package com.se3070_we_43.service;

import com.se3070_we_43.model.Employee;
import com.se3070_we_43.util.CommonConstants;
import com.se3070_we_43.util.DBConnectionUtil;
import com.se3070_we_43.util.QueryUtil;
import com.se3070_we_43.util.XSLTransformUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Initialize logger.
     */
    public static final Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());

    /**
     * The connection.
     */
    private static Connection connection;

    static {
        // create database connection
        createDBConnection();
    }

    /**
     * The employee List.
     */
    private final ArrayList<Employee> employeeList = new ArrayList<>();
    /**
     * The prepared statement.
     */
    private PreparedStatement preparedStatement;

    /**
     * Creates the database connection.
     */
    public static void createDBConnection() {
        try {
            connection = DBConnectionUtil.getDBConnection();
        } catch (SQLException | ClassNotFoundException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public void createEmployeeTable() {
        try {
            Statement statement = connection.createStatement();
            // Drop table if already exists and as per SQL query available in
            // SelectQuery.xml
            statement.executeUpdate(QueryUtil.SelectQuery(CommonConstants.QUERY_ID_DROP_EMPLOYEE_TABLE));
            // Create new employees table as per SQL query available in
            // SelectQuery.xml
            statement.executeUpdate(QueryUtil.SelectQuery(CommonConstants.QUERY_ID_CREATE_EMPLOYEE_TABLE));
        } catch (SAXException | IOException | ParserConfigurationException | SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void dataReaderXML() {
        try {
            int s = XSLTransformUtil.xmlPaths().size();
            for (int i = 0; i < s; i++) {
                Map<String, String> l = XSLTransformUtil.xmlPaths().get(i);
                Employee employee = new Employee();
                employee.setEmployeeId(l.get("XpathEmployeeIDKey"));
                employee.setFullName(l.get("XpathEmployeeNameKey"));
                employee.setAddress(l.get("XpathEmployeeAddressKey"));
                employee.setFacultyName(l.get("XpathFacultyNameKey"));
                employee.setDepartment(l.get("XpathDepartmentKey"));
                employee.setDesignation(l.get("XpathDesignationKey"));
                employeeList.add(employee);
                System.out.println(employee + "\n");
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void addEmployee() {
        try {
            preparedStatement = connection.prepareStatement(QueryUtil.SelectQuery(CommonConstants.QUERY_ID_INSERT_EMPLOYEES));
            connection.setAutoCommit(false);
            for (Employee employee : employeeList) {
                preparedStatement.setString(1, employee.getEmployeeId());
                preparedStatement.setString(2, employee.getFullName());
                preparedStatement.setString(3, employee.getAddress());
                preparedStatement.setString(4, employee.getFacultyName());
                preparedStatement.setString(5, employee.getDepartment());
                preparedStatement.setString(6, employee.getDesignation());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(QueryUtil.SelectQuery(CommonConstants.QUERY_ID_ALL_EMPLOYEES));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getString(1));
                employee.setFullName(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employees.add(employee);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        employeeOutput(employees);
    }

    @Override
    public void getEmployeeByID(String id) {
        Employee employee = new Employee();
        try {
            preparedStatement = connection.prepareStatement(QueryUtil.SelectQuery(CommonConstants.QUERY_ID_GET_EMPLOYEE));
            preparedStatement.setString(1, id);
            ResultSet R = preparedStatement.executeQuery();
            while (R.next()) {
                employee.setEmployeeId(R.getString(1));
                employee.setFullName(R.getString(2));
                employee.setAddress(R.getString(3));
                employee.setFacultyName(R.getString(4));
                employee.setDepartment(R.getString(5));
                employee.setDesignation(R.getString(6));
            }
            ArrayList<Employee> employeeList = new ArrayList<>();
            employeeList.add(employee);
            employeeOutput(employeeList);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void removeEmployee(String id) {
        try {
            preparedStatement = connection.prepareStatement(QueryUtil.SelectQuery(CommonConstants.QUERY_ID_REMOVE_EMPLOYEE));
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public void employeeOutput(ArrayList<Employee> employeeList) {
        try {
            System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t" + "Department" + "\t\t" + "Designation" + "\n");
            System.out.println("================================================================================================================");
            for (Employee employee : employeeList) {
                System.out.println(employee.getEmployeeId() + "\t" + employee.getFullName() + "\t\t" + employee.getAddress() + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t" + employee.getDesignation() + "\n");
                System.out.println("----------------------------------------------------------------------------------------------------------------");
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
