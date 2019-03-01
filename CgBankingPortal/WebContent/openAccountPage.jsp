<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
	<div align="center">
	<form action="openaccount" method="post">
			<table>
				<tr>
					<td>Enter Account Type</td>
					<td><input type="text" name="accountType"  placeholder="Savings or Current"></td>
				</tr>
				<tr>
					<td>Enter Initial Amount</td>
					<td><input type="text" name="initBalance"  placeholder="Enter initial Balance"></td>
				</tr>
				<tr>
					<td><input type="submit"></td>
				</tr>
			</table>
		</form>
	</div>
	<div>
	YOUR GENERATED ACCOUNT NO:- ${requestScope.accountNo}
	</div>
</body>
</html>