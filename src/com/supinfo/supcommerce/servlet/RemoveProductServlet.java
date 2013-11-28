package com.supinfo.supcommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.sun.supcommerce.exception.UnknownProductException;

/**
 * Servlet implementation class RemoveProductServlet Delete a
 * 
 * @author Elka
 * @version 1.0
 * @since SupCommerce 3.2
 */
@WebServlet(displayName = "RemoveProduct", description = "Servlet to delete a product", urlPatterns = { "/auth/removeProduct" })
public class RemoveProductServlet extends HttpServlet {
	private static final long	serialVersionUID		= 1L;
	
	private static final String	ID_POST_PARAM			= "id";
	private static final String	LIST_PRODUCT_SERVLET	= "/listProduct";
	private static final String	SHOW_PRODUCT_SERVLET	= "/showProduct";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveProductServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		// Retrieve the product Id in request parameters
		final Object id = request.getParameter(ID_POST_PARAM);
		
		// Remove matching object in memory
		if (id != null && id instanceof String) {
			try {
				final Long idLong = Long.parseLong((String) id);
				SupProductDao.removeProduct(idLong);
			} catch (UnknownProductException | NumberFormatException e) {
				// Redirect to showProduct with wrong id, where error will be handled
				response.sendRedirect(request.getServletContext().getContextPath() + SHOW_PRODUCT_SERVLET + "?"
						+ ID_POST_PARAM + "=" + id);
			}
		}
		
		// Redirect to list product page
		response.sendRedirect(request.getServletContext().getContextPath() + LIST_PRODUCT_SERVLET);
	}
	
}
