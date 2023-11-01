package cat.institutmarinao.servlet;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cat.institutmarinao.alumnos.Student;
import cat.institutmarinao.repository.Repository;

import java.io.IOException;
import java.util.List;

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
	private Repository repository; // Asegúrate de que el repositorio esté inyectado como un EJB

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén el parámetro "dni" de la solicitud
        String dni = request.getParameter("dni");

        // Verifica si se proporcionó un DNI
        if (dni != null && !dni.isEmpty()) {
            // Recorre la lista de estudiantes y elimina al estudiante correspondiente al DNI
            List<Student> students = repository.getStudents();
            for (Student student : students) {
                if (student.getDni().equals(dni)) {
                    repository.removeStudent(student);
                    break; // Sal del bucle después de eliminar al estudiante
                }
            }
        }

        // Redirige de nuevo al servlet StudentsServlet para actualizar la vista
        response.sendRedirect(request.getContextPath() + "/students");
    }
}

