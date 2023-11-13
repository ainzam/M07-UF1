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

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
	private Repository repository;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dni = request.getParameter("dni");


        if (dni != null && !dni.isEmpty()) {

            List<Student> students = repository.getStudents();
            for (Student student : students) {
                if (student.getDni().equals(dni)) {
                    repository.removeStudent(student);
                    break;
                }
            }
        }


        response.sendRedirect(request.getContextPath() + "/students");
    }
}

