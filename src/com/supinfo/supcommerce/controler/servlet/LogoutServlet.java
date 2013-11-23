package com.supinfo.supcommerce.controler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 * 
 * Invalidate session
 * 
 * @author Elka
 * @version 4.1
 * @since SupCommerce 3.1
 */
@WebServlet(description = "Servlet To Control Logout", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long	serialVersionUID		= 1L;

	private static final String	PARAM_USERNAME_SESSION	= "username";
	private static final String	LOGIN_SERVLET			= "/login";
	private static final String	ROOT_VIEW				= "/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
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

		// Retrieve username session parameter
		final Object sessionUsername = request.getSession().getAttribute(
				PARAM_USERNAME_SESSION);

		// Username exist in session, user is proprely logged :s password ...
		// MD5 ... session token ???
		if (sessionUsername != null && sessionUsername instanceof String) {
			// Destroy session
			request.getSession().invalidate();
			// or just remove "username" attribute
			// request.getSession().removeAttribute("username");

			// Redirect to welcome page
			response.sendRedirect(request.getServletContext().getContextPath()
					+ ROOT_VIEW);
		} else
			// Redirect to login page
			response.sendRedirect(request.getServletContext().getContextPath()
					+ LOGIN_SERVLET);
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
		doGet(request, response);
	}

}
