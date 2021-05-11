<%@page import="com.Controller.ProductAdminController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/products.js"></script>

</head>
<body>

	<div class="container"><div class="row"><div class="col-6"> 
		<h1>Product Management</h1>
		
		<form id="formItem" name="formItem">
 	     
 		 Product name: <input id="productname" name="productname" type="text" class="form-control form-control-sm">
 		<br>
 		 Product category: <input id="productcategory" name="productcategory" type="text" class="form-control form-control-sm">
 		 <br>
 		 Product description: <input id="description" name="description" type="text" class="form-control form-control-sm">
 		<br>
 		 Product price: <input id="price" name="price" type="text" class="form-control form-control-sm">
 		<br>

 		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">

		</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemsGrid">
		<%
 			ProductAdminController itemObj = new ProductAdminController();
 			out.print(itemObj.readProducts()); 
 		%>
	</div>
	</div> </div> </div> 

</body>
</html>