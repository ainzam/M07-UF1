package cat.institutmarianao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuessColor2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String initColor;

	@Override
	public void init() throws ServletException {
		// S'ha configurat un paràmetre que conté el color a endevinar:
		initColor = getServletConfig().getInitParameter("color");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {

			// L'usuari ha seleccionat un color i ho ha enviat.
			String paramColor = request.getParameter("color");

			if (paramColor == null) {
				RequestDispatcher rs = request.getRequestDispatcher("GuessColor2.html");
				rs.forward(request, response);
			} else if (initColor.equalsIgnoreCase(paramColor.toLowerCase())) {
				RequestDispatcher rs = request.getRequestDispatcher("guanyar.html");
				rs.forward(request, response);
			}else {
				RequestDispatcher rs = request.getRequestDispatcher("perder.html");
				rs.forward(request, response);
			}
		}
	}
}