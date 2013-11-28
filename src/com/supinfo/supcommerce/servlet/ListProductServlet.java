package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

/**
 * <b>ListProductServlet</b>
 * <p>
 * List all products registered in memory (through SupCommerce.jar)
 * </p>
 * 
 * @author Elka
 * @version 1.1
 * @since SupCommerce 2.1
 */
@WebServlet(displayName = "ListProduct", description = "Servlet to List all registered products", urlPatterns = "/listProduct")
public class ListProductServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	
	private static final String	ATT_PRODUCTS_REQ	= "products";
	
	private static final String	LIST_PRODUCT_VIEW	= "/WEB-INF/layout/listProduct.jsp";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListProductServlet() {
		super();
	}
	
	/**
	 * Handle all HTTP methods (GET, POST, PUT, DELETE, ...).
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		// Retrieve Product list
		final List<SupProduct> products = SupProductDao.getAllProducts();
		
		// Place product list in request paramters
		request.setAttribute(ATT_PRODUCTS_REQ, products);
		
		// Forward to product list view
		request.getRequestDispatcher(LIST_PRODUCT_VIEW).forward(request, response);
	}
	
}
