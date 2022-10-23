package com.se3070_we_43.model;

/**
 * The Class Employee.
 */
public class Employee {

	/** The employee id. */
	private String employeeId;
	
	/** The full name. */
	private String fullName;
	
	/** The address. */
	private String address;
	
	/** The faculty name. */
	private String facultyName;
	
	/** The department. */
	private String department;
	
	/** The designation. */
	private String designation;

	/**
	 * Gets the employee id.
	 *
	 * @return the employee id
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * Sets the employee id.
	 *
	 * @param employeeId the new employee id
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the faculty name.
	 *
	 * @return the faculty name
	 */
	public String getFacultyName() {
		return facultyName;
	}

	/**
	 * Sets the faculty name.
	 *
	 * @param facultyName the new faculty name
	 */
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Sets the department.
	 *
	 * @param department the new department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", fullName=" + fullName + ", address=" + address
				+ ", facultyName=" + facultyName + ", department=" + department + ", designation=" + designation + "]";
	}
	
}
