package cat.institutmarinao.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import cat.institutmarinao.alumnos.Student;
import cat.institutmarinao.repository.Repository;

@WebServlet(name = "register", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private Repository repository;// Asegúrate de que el repositorio esté inyectado como un EJB
	@Resource
	Validator validator;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Obtén el parámetro "cycle" de la solicitud
	    String cycle = request.getParameter("cycle");
	    // Obtén la lista de módulos correspondiente al ciclo desde el repositorio
	    Set<String> modules = new HashSet<>(repository.getModules(cycle));

	    // Agrega los datos como atributos a la solicitud
	    request.setAttribute("cycle", cycle);
	    List<String> modulesList = new ArrayList<>(modules);
	    request.setAttribute("modules", modulesList);

	    // Verifica si hay módulos seleccionados en la solicitud (por ejemplo, después de un error de validación)
	    String[] modulesChecked = request.getParameterValues("modules");
	    if (modulesChecked != null) {
	        // Establece los módulos seleccionados como atributo de solicitud para conservarlos
	        request.setAttribute("selectedModules", Arrays.asList(modulesChecked));
	    }

	    // Realiza un forward a la vista register.jsp
	    request.getRequestDispatcher("/register.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// Obtén los datos del formulario
		String dni = request.getParameter("dni");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String cycle = request.getParameter("cycle");
		String[] modulesChecked = request.getParameterValues("modules");
	    if (modulesChecked == null) {
	        modulesChecked = new String[0]; // Inicializa como un arreglo vacío si no se selecciona ningún módulo
	    }
		Set<String> selectedModules = new HashSet<>(Arrays.asList(modulesChecked));



		// Crea una instancia de Student y configura los valores
		Student student = new Student();
		student.setDni(dni);
		student.setName(name);
		student.setSurname(surname);
		student.setEmail(email);
		student.setCycle(cycle);
		student.setModules(modulesChecked);

		// Valida el objeto Student
		Set<ConstraintViolation<Student>> errors = validator.validate(student);

		if (errors.isEmpty()) {
			// Si no hay violaciones, agrega el estudiante al repositorio y redirige
			repository.addStudent(student);
			response.sendRedirect(request.getContextPath() + "/students");
		} else {
			// Establece los datos en los atributos de la solicitud para conservarlos
			request.setAttribute("dni", dni);
			request.setAttribute("name", name);
			request.setAttribute("surname", surname);
			request.setAttribute("email", email);
			request.setAttribute("cycle", cycle);
			request.setAttribute("selectedModules", selectedModules); // Establece los módulos seleccionados
	        List<String> modules = repository.getModules(cycle);
	        request.setAttribute("modules", modules);

			// Crea un mapa de errores y coloca las violaciones en él
			Map<String, String> errorsMap = new HashMap<>();
			for (ConstraintViolation<Student> violation : errors) {
				errorsMap.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			// Agrega el mapa de errores a la solicitud
			request.setAttribute("errorsMap", errorsMap);

			// Si hay violaciones, agrega las violaciones como atributo a la solicitud y
			// redirige a register.jsp
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}
}
