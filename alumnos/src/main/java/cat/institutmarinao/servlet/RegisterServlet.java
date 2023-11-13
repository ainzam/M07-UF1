package cat.institutmarinao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import cat.institutmarinao.alumnos.Student;
import cat.institutmarinao.repository.Repository;

@WebServlet(name = "register", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private Repository repository;
	@Resource
	Validator validator;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
	    String cycle = request.getParameter("cycle");
	    
	    Set<String> modules = new HashSet<>(repository.getModules(cycle));

	    
	    request.setAttribute("cycle", cycle);
	    List<String> modulesList = new ArrayList<>(modules);
	    request.setAttribute("modules", modulesList);

	    
	    String[] modulesChecked = request.getParameterValues("modules");
	    if (modulesChecked != null) {

	        request.setAttribute("selectedModules", Arrays.asList(modulesChecked));
	    }


	    request.getRequestDispatcher("/register.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		

		String dni = request.getParameter("dni");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String cycle = request.getParameter("cycle");
		String[] modulesChecked = request.getParameterValues("modules");
	    if (modulesChecked == null) {
	        modulesChecked = new String[0];
	    }
		Set<String> selectedModules = new HashSet<>(Arrays.asList(modulesChecked));


		Student student = new Student();
		student.setDni(dni);
		student.setName(name);
		student.setSurname(surname);
		student.setEmail(email);
		student.setCycle(cycle);
		student.setModules(modulesChecked);


		Set<ConstraintViolation<Student>> errors = validator.validate(student);

		if (errors.isEmpty()) {

			repository.addStudent(student);
			response.sendRedirect(request.getContextPath() + "/students");
		} else {

			request.setAttribute("dni", dni);
			request.setAttribute("name", name);
			request.setAttribute("surname", surname);
			request.setAttribute("email", email);
			request.setAttribute("cycle", cycle);
			request.setAttribute("selectedModules", selectedModules);
	        List<String> modules = repository.getModules(cycle);
	        request.setAttribute("modules", modules);


			Map<String, String> errorsMap = new HashMap<>();
			for (ConstraintViolation<Student> violation : errors) {
				errorsMap.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			
			request.setAttribute("errorsMap", errorsMap);


			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}
}
