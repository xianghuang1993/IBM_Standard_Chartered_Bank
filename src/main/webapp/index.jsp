<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		Hotel Home Page 
		<table border="1">
			<tr>
				<td>Action</td>
				<td>Link</td>
			</tr>
			<tr>
				<td>Add Room</td>
				<td><a href="<%=request.getContextPath() %>/addroom.jsp" target="_blank">Add Room</a></td>
			</tr>
			<tr>
				<td>Store Booking</td>
				<td><a href="<%=request.getContextPath() %>/storebooking.jsp" target="_blank">Store Booking</a></td>
			</tr>
			<tr>
				<td>Find Available Rooms</td>
				<td><a href="<%=request.getContextPath() %>/findavailablerooms.jsp" target="_blank">Find Available Rooms</a></td>
			</tr>
			<tr>
				<td>Find Bookings For Guest</td>
				<td><a href="<%=request.getContextPath() %>/findbookingsforguest.jsp" target="_blank">Find Bookings For Guest</a></td>
			</tr>
			<tr>
				<td>Reset Hotel Info</td>
				<td><a href="<%=request.getContextPath() %>/resethotelinfo.jsp" target="_blank">Reset Hotel Info</a></td>
			</tr>
		</table>
	</body>
</html>