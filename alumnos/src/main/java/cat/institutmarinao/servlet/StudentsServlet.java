package cat.institutmarinao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cat.institutmarinao.repository.Repository;

@WebServlet(name="students")
public class StudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        request.setAttribute("cycles", Repository.getCycles());

        
        request.setAttribute("students", Repository.getStudents());

        
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
}

