/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.institutmarianao.servlet;

import java.io.IOException;
import java.util.List;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Toni
 */
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = -6905915821521730260L;

	@Inject
	private UserService userService;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");

	    List<User> users;

	    try {
	        if (username != null && !username.isEmpty()) {
	            users = List.of(userService.findUserByUsername(username));
	        } else if (email != null && !email.isEmpty()) {
	            users = List.of(userService.findUserByEmail(email));
	        } else {
	            users = userService.getAllUsers();
	        }
	    } catch (Exception e) {
	    	users = userService.getAllUsers();
	        request.setAttribute("message", "No se encuetra el usuario deseado.");
	    }

	    request.setAttribute("users", users);
	    request.getRequestDispatcher("users.jsp").forward(request, response);
	}



	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
