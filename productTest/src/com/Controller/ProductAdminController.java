package com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ProductAdminController {
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafprojtest", "root", "971821221"); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 

	//---------------Retrieve------------------------------------------------------
	public String readProducts() 
	{ 
		 String output = ""; 
		 
		 try
		 { 
			 Connection con = connect(); 
		 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
		 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Product Name</th><th>Product Category</th><th>Product Description</th><th>Price</th><th>Update</th><th>Remove</th></tr>"; 
		 
			 String query = "select * from products"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
		 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 int productid = rs.getInt("productid");
				 String productname = rs.getString("productname");
				 String productcategory = rs.getString("productcategory"); 
				 String description = rs.getString("description");
				 String price = Double.toString(rs.getDouble("price"));
				 
				 // Add into the html table
				
				 output += "<tr><td>" + productname + "</td>"; 
				 output += "<td>" + productcategory + "</td>"; 
				 output += "<td>" + description + "</td>"; 
				 output += "<td>" + price + "</td>"; 
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-itemid='" + productid + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-itemid='" + productid + "'></td></tr>";  
		 } 
		 
		con.close(); 
		 
		// Complete the html table
		 output += "</table>"; 
	} 
	catch (Exception e) 
	{ 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
	} 
		 return output; 
} 
	
	
	//---------------Insert------------------------------------------------------
	public String addProducts(String productname, String productcategory, String description, String price) 
	{ 
		String output = ""; 
	
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
 
			// create a prepared statement
			String query = " insert into pafprojtest.products (`productid`,`productname`,`productcategory`,`description`,`price`)"
					+ " values (?,?,?,?,?)"; 
 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, productname); 
			preparedStmt.setString(3, productcategory); 
			preparedStmt.setString(4,description);
			preparedStmt.setDouble(5, Double.parseDouble(price));
			
			// execute the statement
			preparedStmt.execute(); 
			 con.close(); 
			 String newItems = readProducts(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
			 output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			 System.err.println(e.getMessage());
		} 
			 
		return output; 
	}

	//--------------Update-------------------------------------------------------
	public String updateProducts(String productid, String productname, String productcategory, String description, String price)
	{ 
		 String output = ""; 
		 try
		 { 
			 	Connection con = connect(); 
		 
			 	if (con == null) 
			 	{return "Error while connecting to the database for updating."; } 
		 
			 	// create a prepared statement
			 	String query = "UPDATE products SET productname=?,productcategory=?,description=?,price=? WHERE productid=?"; 
		 
			 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 	// binding values
			 	preparedStmt.setString(1, productname); 
			 	preparedStmt.setString(2, productcategory);  
			 	preparedStmt.setString(3, description); 
			 	preparedStmt.setDouble(4, Double.parseDouble(price));
			 	preparedStmt.setInt(5, Integer.parseInt(productid)); 
			
			 	// execute the statement
			 	preparedStmt.execute(); 
			 	con.close(); 
				 String newItems = readProducts(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output; 
		 
	}

	
	//-------------Delete--------------------------------------------------------------
	public String deleteProducts(String productid) 
	 { 
		String output = ""; 
	 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement
			String query = "delete from products where productid=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			
	 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(productid)); 
	 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			 String newItems = readProducts(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
		 System.err.println(e.getMessage()); 
	 } 
		
	 return output;
	 
	 } 

}
