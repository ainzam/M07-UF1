package cat.institutmarinao.servlet;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cat.institutmarinao.alumnos.Student;
import cat.institutmarinao.repository.Repository;

@WebServlet(name = "students", urlPatterns = { "/students" })
public class StudentsServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
	@EJB
    private Repository repository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> cycles = repository.getCycles();


        List<Student> students = repository.getStudents();


        request.setAttribute("cycles", cycles);
        request.setAttribute("students", students);


        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
}
