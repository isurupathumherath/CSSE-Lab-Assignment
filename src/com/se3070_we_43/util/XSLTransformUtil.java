package com.se3070_we_43.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

public class XSLTransformUtil extends CommonUtil {

	private static final ArrayList<Map<String, String>> responseList = new ArrayList<Map<String, String>>();

	private static Map<String, String> map = null;

	public static void requestTransform() throws Exception {

		Source employeeRequest = new StreamSource(new File(CommonConstants.EMPLOYEE_REQUEST));
		Source employeeModified = new StreamSource(new File(CommonConstants.EMPLOYEE_MODIFIED));
		Result employeeResponse = new StreamResult(new File(CommonConstants.EMPLOYEE_RESPONSE));
		TransformerFactory.newInstance().newTransformer(employeeModified).transform(employeeRequest, employeeResponse);
	}

	public static ArrayList<Map<String, String>> xmlxPaths() throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(CommonConstants.EMPLOYEE_RESPONSE);
		XPath xPath = XPathFactory.newInstance().newXPath();
		int count = Integer.parseInt((String) xPath.compile("count(//Employees/Employee)").evaluate(document, XPathConstants.STRING));
		for (int i = 1; i <= count; i++) {
			map = new HashMap<String, String>();
			map.put("XpathEmployeeIDKey", (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathEmployeeNameKey", (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathEmployeeAddressKey",
					(String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathFacultyNameKey", (String) xPath.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathDepartmentKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Department/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathDesignationKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Designation/text()")
					.evaluate(document, XPathConstants.STRING));
			responseList.add(map);
		}
		return responseList;
	}
}
