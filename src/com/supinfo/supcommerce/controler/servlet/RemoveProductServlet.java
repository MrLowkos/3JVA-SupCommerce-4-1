package com.supinfo.supcommerce.controler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.sun.supcommerce.exception.UnknownProductException;

/**
 * Servlet implementation class RemoveProductServlet
 * 
 * Delete a product
 * 
 * @author Elka
 * @version 4.1
 * @since SupCommerce 3.2
 */
@WebServlet(name = "RemoveProductServlet", description = "Servlet To Delete A Product", urlPatterns = { "/auth/removeProduct" })
public class RemoveProductServlet extends HttpServlet {
	private static final long	serialVersionUID		= 1L;

	private static final String	PARAM_ID_REQ			= "id";
	private static final String	LIST_PRODUCT_SERVLET	= "/listProduct";
	private static final String	SHOW_PRODUCT_SERVLET	= "/showProduct";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveProductServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Retrieve the product Id in request parameters
		final Object idParam = request.getParameter(PARAM_ID_REQ);

		// Remove matching object in memory
		if (idParam != null && idParam instanceof String) {
			try {
				final Long idLong = Long.parseLong((String) idParam);
				SupProductDao.removeProduct(idLong);
			} catch (UnknownProductException | NumberFormatException e) {
				// Forward to showProduct with wrong id, where error will be
				// handled
				request.getRequestDispatcher(
						SHOW_PRODUCT_SERVLET + "?" + PARAM_ID_REQ + "="
								+ idParam).forward(request, response);
			}
		}

		// Redirect to list product page
		response.sendRedirect(request.getServletContext().getContextPath()
				+ LIST_PRODUCT_SERVLET);
	}

}
