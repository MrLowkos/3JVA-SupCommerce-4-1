package com.supinfo.supcommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

/**
 * Servlet implementation class AddProductServlet Register product with specifics settings in memory (via
 * SupCommerce.jar)
 * 
 * @author Elka
 * @version 1.0
 * @since SupCommerce 3.1
 */
@WebServlet(description = "Servlet To Add A Specific Product", urlPatterns = { "/auth/addProduct" })
public class AddProductServlet extends HttpServlet {
	private static final long	serialVersionUID		= 1L;
	
	private static final String	ID_GET_PARAM			= "id";
	
	private static final String	NAME_POST_PARAM			= "product-name";
	private static final String	CONTENT_POST_PARAM		= "product-content";
	private static final String	PRICE_POST_PARAM		= "product-price";
	
	private static final String	NAME_REQ_ATT			= "name";
	private static final String	CONTENT_REQ_ATT			= "content";
	private static final String	PRICE_REQ_ATT			= "price";
	private static final String	ERROR_NAME_REQ_ATT		= "nameError";
	private static final String	ERROR_CONTENT_REQ_ATT	= "contentError";
	private static final String	ERROR_PRICE_REQ_ATT		= "priceError";
	
	private static final String	SHOW_PRODUCT_SERVLET	= "/showProduct";
	private static final String	ADD_PRODUCT_VIEW		= "/WEB-INF/layout/addProduct.jsp";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductServlet() {
		super();
	}
	
	/**
	 * Handles <code>GET</code> HTTP method Forward to appropriate view
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(ADD_PRODUCT_VIEW).forward(request, response);
	}
	
	/**
	 * Handles <code>POST</code> HTTP method Process product creation form
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		String nameError = "";
		String contentError = "";
		String priceError = "";
		
		// Retrieve POST parameters
		final Object name = request.getParameter(NAME_POST_PARAM);
		final Object content = request.getParameter(CONTENT_POST_PARAM);
		final Object price = request.getParameter(PRICE_POST_PARAM);
		
		// New empty product
		final SupProduct product = new SupProduct();
		
		// Check data integrity (Never Trust User Input)
		// - product name
		if (name != null && name instanceof String)
			if (name != "")
				product.setName((String) name);
			else
				nameError = "Empty flied !";
		else
			nameError = "Invalid name !";
		
		// - product content
		if (content != null && content instanceof String)
			if (content != "")
				product.setContent((String) content);
			else
				contentError = "Empty flied !";
		else
			contentError = "Invalid description !";
		
		// - product price (Float)
		if (price != null && price instanceof String) {
			if (price != "") {
				try {
					product.setPrice(Float.parseFloat((String) price));
				} catch (NullPointerException e) {
					priceError = "Invalid price !";
				} catch (NumberFormatException e) {
					priceError = "Not a float number !";
				}
			} else {
				priceError = "Empty flied !";
			}
		} else
			priceError = "Invalid price !";
		
		// Everithing is ok
		if (nameError == "" && contentError == "" && priceError == "") {
			// Generate ID and add product in memory
			SupProductDao.addProduct(product);
			
			// Redirection to /showProduct?id=X
			response.sendRedirect(request.getServletContext().getContextPath() + SHOW_PRODUCT_SERVLET + "?"
					+ ID_GET_PARAM + "=" + product.getId());
		}
		// Invalid form completion - Set request attributes and forward to addProduct.jsp
		else {
			// Save correct parameters and set errors
			if (nameError != "")
				request.setAttribute(ERROR_NAME_REQ_ATT, nameError);
			else
				request.setAttribute(NAME_REQ_ATT, name);
			
			if (contentError != "")
				request.setAttribute(ERROR_CONTENT_REQ_ATT, contentError);
			else
				request.setAttribute(CONTENT_REQ_ATT, content);
			
			if (priceError != "")
				request.setAttribute(ERROR_PRICE_REQ_ATT, priceError);
			else
				request.setAttribute(PRICE_REQ_ATT, price);
			
			// Forward
			request.getRequestDispatcher(ADD_PRODUCT_VIEW).forward(request, response);
		}
	}
	
}
