package com.supinfo.supcommerce.controler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.sun.supcommerce.exception.UnknownProductException;

/**
 * Servlet implementation class ShowProductServlet
 * 
 * Show product registered in memory (via SupCommerce.jar) by "id"
 * <code>GET</code> parameter use contextPath/showProduct?id=
 * 
 * @author Elka
 * @version 4.1
 * @since SupCommerce 2.1
 */
@WebServlet(description = "Servlet To Show A Registered Product By ID", urlPatterns = "/showProduct")
public class ShowProductServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;

	private static final String	PARAM_ID_REQ		= "id";
	private static final String	ATT_ERROR_REQ		= "error";
	private static final String	ATT_PRODUCT_REQ		= "product";

	private static final String	SHOW_PRODUCT_VIEW	= "/WEB-INF/layout/showProduct.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowProductServlet() {
		super();
	}

	/**
	 * Handles <code>GET</code> HTTP method
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Recover id parameter through url
		final String idParam = request.getParameter(PARAM_ID_REQ);

		// New empty product
		SupProduct product = new SupProduct();

		// Init error message
		String error = "";

		if (idParam != null && idParam instanceof String) {
			try {
				final Long idLong = Long.parseLong(idParam);
				product = SupProductDao.findProductById(idLong);
			} catch (UnknownProductException e) {
				error = e.getMessage();
			} catch (NumberFormatException e) {
				error = "\"id\" parameter should be a number !";
			}
		} else {
			error = "No \"id\" parameter specified.";
		}

		request.setAttribute(ATT_ERROR_REQ, error);
		request.setAttribute(ATT_PRODUCT_REQ, product);

		request.getRequestDispatcher(SHOW_PRODUCT_VIEW).forward(request,
				response);
	}
}
