package cat.institutmarinao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cat.institutmarinao.alumnos.Student;
import cat.institutmarinao.repository.Repository;

@WebServlet(name = "register", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private Repository repository;// Asegúrate de que el repositorio esté inyectado como un EJB

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtén el parámetro "cycle" de la solicitud
		String cycle = request.getParameter("cycle");
		// Obtén la lista de módulos correspondiente al ciclo desde el repositorio
		List<String> modules = repository.getModules(cycle);

		// Agrega los datos como atributos a la solicitud
		request.setAttribute("cycle", cycle);
		request.setAttribute("modules", modules);

		// Realiza un forward a la vista register.jsp
		request.getRequestDispatcher("/register.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtén los datos del formulario
		String dni = request.getParameter("dni");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String cycle = request.getParameter("cycle");
		String[] modules = request.getParameterValues("modules");

		// Validación de los datos
		List<String> errors = new ArrayList<>();

		if (dni == null || dni.isEmpty() || !dni.matches("[0-9]{7,8}[A-Za-z]")) {
			errors.add("El DNI debe tener 7 u 8 dígitos seguidos de una letra.");
		}

		if (name == null || name.isEmpty() || name.length() > 200) {
			errors.add("El nombre no puede estar en blanco y debe tener como máximo 200 caracteres.");
		}

		if (surname == null || surname.isEmpty() || surname.length() > 200) {
			errors.add("El apellido no puede estar en blanco y debe tener como máximo 200 caracteres.");
		}

		if (email != null && !email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			errors.add("El correo electrónico no tiene un formato válido.");
		}

		if (cycle == null || cycle.isEmpty()) {
			errors.add("Debes seleccionar un ciclo.");
		}

		if (modules == null || modules.length == 0) {
			errors.add("Debes seleccionar al menos un módulo.");
		}

		if (errors.isEmpty()) {
			// Si no hay errores de validación, crea un objeto Student y agrégalo al
			// repositorio
			Student student = new Student();
			student.setDni(dni);
			student.setName(name);
			student.setSurname(surname);
			student.setEmail(email);
			student.setCycle(cycle);
			student.setModules(modules);
			repository.addStudent(student);

			// Redirige a StudentsServlet para actualizar la vista
			response.sendRedirect(request.getContextPath() + "/students");
		} else {
			// Si hay errores, agrega los errores como atributo a la solicitud y redirige a
			// register.jsp
			request.setAttribute("errors", errors);
			doGet(request, response); // Redirige nuevamente a la página de registro con los errores
		}
	}
}
