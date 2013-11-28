package com.supinfo.supcommerce.controler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 * 
 * Create a new session and a "username" attribute in this one.
 * 
 * @author Elka
 * @version 4.1
 * @since SupCommerce 2.2
 */
@WebServlet(description = "Servlet To Control Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long	serialVersionUID		= 1L;

	private static final String	PARAM_USERNAME_POST		= "username";
	private static final String	LIST_PRODUCT_SERVLET	= "/listProduct";
	private static final String	LOGIN_VIEW				= "/WEB-INF/layout/login.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// Forward to login page
		request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
	}

	/**
	 * Handles <code>POST</code> HTTP method
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Retrieve form parameter
		final Object usernameParam = request.getParameter(PARAM_USERNAME_POST);

		// "username" (String) exist in request POST parameters
		if (usernameParam != null && usernameParam instanceof String) {
			// Insert it in session attribute
			request.getSession().setAttribute(PARAM_USERNAME_POST,
					usernameParam);
			// Redirect to products list
			response.sendRedirect(request.getServletContext().getContextPath()
					+ LIST_PRODUCT_SERVLET);
		}
		// No POST parameters
		else
			// Forward to login page
			request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);

	}

}
