<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/Employees">
	<html>
		<head><title>Real's HowTo</title></head>
		<body>
			<table border="1">
				<tr>
					<th>name</th>
					<th>salary</th>
					<th>hiredate</th>
				</tr>
				<xsl:for-each select="/Employees/employee">
					<tr>
						<td><xsl:value-of select="name"/></td>
						<td><xsl:value-of select="salary"/></td>						
						<td><xsl:value-of select="hiredate/year"/>-<xsl:value-of select="hiredate/month"/>-<xsl:value-of select="hiredate/day"/></td>
					</tr>
				</xsl:for-each>
			</table>
		</body>
	</html>
	</xsl:template>
</xsl:stylesheet>