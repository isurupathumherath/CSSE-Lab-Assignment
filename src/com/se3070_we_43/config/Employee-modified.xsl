<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

	<xsl:template match="employees">
		<xsl:element name="Employees">
			<xsl:apply-templates select="*" />
		</xsl:element>
	</xsl:template>
	<xsl:template match="employee_profile">
		<xsl:element name="Employee">
			<xsl:template match="employee_id">
				<xsl:element name="EmployeeID">
					<xsl:value-of select="employee_id/text()" />
				</xsl:element>
			</xsl:template>
			<xsl:template match="employee_name">
				<xsl:element name="EmployeeFullName">
					<xsl:value-of select="concat(employee_name/first_name/text(),' ',employee_name/last_name/text())" />
				</xsl:element>
			</xsl:template>
			<xsl:template match="address">
				<xsl:element name="EmployeeFullAddress">
					<xsl:value-of select="concat(address/no/text(),',',address/address_line1/text(),',',address/address_line2/text())" />
				</xsl:element>
			</xsl:template>
			<xsl:template match="designation">
				<xsl:element name="Designation">
					<xsl:value-of select="designation/text()" />
				</xsl:element>
			</xsl:template>
			<xsl:template match="faculty">
				<xsl:element name="FacultyName">
					<xsl:value-of select="faculty/@name" />
				</xsl:element>
				<xsl:element name="Department">
					<xsl:value-of select="faculty/department/text()" />
				</xsl:element>
			</xsl:template>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>