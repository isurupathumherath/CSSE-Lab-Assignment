package com.se3070_we_43.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.se3070_we_43.model.Employee;
import com.se3070_we_43.util.CommonConstants;
import com.se3070_we_43.util.DBConnectionUtil;
import com.se3070_we_43.util.QueryUtil;
import com.se3070_we_43.util.XSLTransformUtil;



public class EmployeeServiceImpl implements EmployeeService {

	/**  Initialize logger. */
	public static final Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());
	
	/** The employee List. */
	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

	/** The connection. */
	private static Connection connection;

	/** The statement. */
	private static Statement statement;

	/** The prepared statement. */
	private PreparedStatement preparedStatement;
	
	static {
		// create table or drop if exist
		createEmployeeTable();
	}

	/**
	 * Creates the employee table.
	 */
	public static void createEmployeeTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.getEmployeeById(CommonConstants.QUERY_ID_DROP_EMPLOYEE_TABLE));
			// Create new employees table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.getEmployeeById(CommonConstants.QUERY_ID_CREATE_EMPLOYEE_TABLE));
			
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
	
	@Override
	public void addEmployee() {
		
		try {
			int s = XSLTransformUtil.xmlxPaths().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = XSLTransformUtil.xmlxPaths().get(i);
				Employee employee = new Employee();
				employee.setEmployeeId(l.get("XpathEmployeeIDKey"));
				employee.setFullName(l.get("XpathEmployeeNameKey"));
				employee.setAddress(l.get("XpathEmployeeAddressKey"));
				employee.setFacultyName(l.get("XpathFacultyNameKey"));
				employee.setDepartment(l.get("XpathDepartmentKey"));
				employee.setDesignation(l.get("XpathDesignationKey"));
				employeeList.add(employee);
				System.out.println(employee.toString() + "\n");
			}
		} catch (Exception e) {
		}
		
	}

	@Override
	public void getEmployees() {

	}

	@Override
	public void getEmployeeByID(String id) {
		
		Employee employee = new Employee();
		try {
			preparedStatement = connection.prepareStatement(QueryUtil.getEmployeeById(CommonConstants.QUERY_ID_GET_EMPLOYEE));
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
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(employee);
			employeeOutput(employeeList);
		} catch (Exception ex) {
		}
	}

	@Override
	public void updateEmployee(String id) {
		
	}

	@Override
	public void removeEmployee(String id) {
		try {
//			preparedStatement = connection.prepareStatement(QueryUtil.getEmployeeById(CommonConstants.QUERY_ID_REMOVE_EMPLOYEE));
//			preparedStatement.setString(1, id);
//			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void employeeOutput(ArrayList<Employee> employeeList){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(Employee employee : employeeList){
			System.out.println(employee.getEmployeeId() + "\t" + employee.getFullName() + "\t\t"
					+ employee.getAddress() + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
					+ employee.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
	
}
