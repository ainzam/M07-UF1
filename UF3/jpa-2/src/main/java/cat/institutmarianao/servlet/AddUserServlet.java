package cat.institutmarianao.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/AddUserForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Integer rank = Integer.parseInt(request.getParameter("rank"));

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRank(rank);
        newUser.setActive(true);
        newUser.setCreatedOn(Timestamp.from(Instant.now()));

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(newUser);

        if (!violations.isEmpty()) {
            request.setAttribute("violations", violations);
            request.getRequestDispatcher("/AddUserForm.jsp").forward(request, response);
            return; 
        } else {
            userService.create(newUser);
            response.sendRedirect(request.getContextPath() + "/UsersServlet");
        }
    }
}
