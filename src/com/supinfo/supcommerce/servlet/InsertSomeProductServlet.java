package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

/**
 * <b>InsertSomeProductServlet</b>
 * <p>
 * Register random generated product in memory (through SupCommerce.jar)
 * </p>
 * 
 * @author Elka
 * @version 1.1
 * @since SupCommerce 2.1
 */
@WebServlet(displayName = "InsertSomeProduct", description = "Servlet to insert random product", urlPatterns = "/auth/basicInsert")
public class InsertSomeProductServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	
	private static final String	LIST_PRODUCT_VIEW	= "/listProduct";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertSomeProductServlet() {
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
		
		// Random generator (optional) + protect the reference of generator with final keyword
		final Random rand = new Random();
		
		// New product's creation
		final SupProduct product = new SupProduct();
		// Set product properties randomly
		product.setName("Product-" + rand.nextInt(100));
		product.setContent("Product information of " + product.getName() + " have to be set.");
		product.setPrice(rand.nextFloat() + rand.nextInt(100));
		
		// Generate ID and add product in memory
		SupProductDao.addProduct(product);
		
		// Redirect to listProduct.jsp
		response.sendRedirect(request.getServletContext().getContextPath() + LIST_PRODUCT_VIEW);
		
	}
}
