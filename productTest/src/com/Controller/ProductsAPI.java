package com.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ProductsAPI")
public class ProductsAPI extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductAdminController itemObj = new ProductAdminController();
		String output = itemObj.addProducts(request.getParameter("productname"), 
				 request.getParameter("productcategory"), 
				request.getParameter("description"), 
				request.getParameter("price")); 
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}

		 
		 
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductAdminController itemObj = new ProductAdminController();
		Map paras = getParasMap(request); 
		 String output = itemObj.updateProducts(paras.get("hidItemIDSave").toString(), 
		 paras.get("productname").toString(), 
		 paras.get("productcategory").toString(), 
		paras.get("description").toString(), 
		paras.get("price").toString()); 
		response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductAdminController itemObj = new ProductAdminController();
		Map paras = getParasMap(request); 
		 String output = itemObj.deleteProducts(paras.get("productid").toString()); 
		response.getWriter().write(output);
	}

}
