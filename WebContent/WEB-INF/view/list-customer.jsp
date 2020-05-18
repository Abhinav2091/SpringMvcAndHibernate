<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel ="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
<title>List Customer Page</title>
</head>
<body>
<div id="wrapper">
<div id="header">
<h2>CRM - Customer Relationship Manager</h2>
</div>
</div>

<div id="container">
<div id="content">

<!--  put new add CUstomer button -->
<input type="button" value ="Add Customer" class="add-button" onclick="window.location.href='showFormForAdd'; return false;">

    <!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>

<!-- add html taable here -->
<table>
<tr>
<th>First Name </th>
<th>Last Name </th>
<th>Email </th>
<th>Action </th>
</tr>
<!-- loop over and print our customer -->
<c:forEach var="tempCustomer" items="${customer}">
<!-- Create a update link with customer Id  -->
<c:url var="updateLink" value="/customer/showFormForUpdate">
<c:param name ="customerId" value="${tempCustomer.id }"/>
</c:url>
<!-- Create a delete link with customer Id  -->
<c:url var="deleteLink" value="/customer/delete">
<c:param name ="customerId" value="${tempCustomer.id }"/>
</c:url>

<tr>
<td>${tempCustomer.firstName}</td>
<td>${tempCustomer.lastName}</td>
<td>${tempCustomer.email}</td>
<td>
<!-- display the update link -->
<a href="${updateLink}">Update</a>

<!-- display the delete link -->
<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this Customer!!!'))) return false">Delete</a>
</td>
</tr>

</c:forEach>
</table>
</div>
</div>
</body>
</html>