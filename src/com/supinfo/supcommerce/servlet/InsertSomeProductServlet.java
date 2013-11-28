package com.supinfo.supcommerce.controler.servlet;

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
 * Servlet implementation class InsertSomeProductServlet
 * 
 * Register a random generated product in memory (via SupCommerce.jar)
 * 
 * @author Elka
 * @version 4.1
 * @since SupCommerce 2.1
 */
@WebServlet(description = "Servlet To Insert Random Product", urlPatterns = "/auth/basicInsert")
public class InsertSomeProductServlet extends HttpServlet {
	private static final long	serialVersionUID		= 1L;

	private static final String	LIST_PRODUCT_SERVLET	= "/listProduct";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertSomeProductServlet() {
		super();
	}

	/**
	 * Handles all HTTP methods (GET, POST, PUT, DELETE, ...).
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Random generator + protect the reference (optional)
		final Random rand = new Random();

		// New empty product
		final SupProduct product = new SupProduct();
		// Set product properties randomly
		product.setName("Product-" + rand.nextInt(100));
		product.setContent("Product's Information of " + product.getName());
		product.setPrice(rand.nextFloat() + rand.nextInt(1000));

		// Generate ID and add product in memory
		SupProductDao.addProduct(product);

		// Redirect to list product
		response.sendRedirect(request.getServletContext().getContextPath()
				+ LIST_PRODUCT_SERVLET);
	}

}
