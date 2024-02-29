package cat.institutmarianao.servlet;

import java.io.IOException;
import cat.institutmarianao.service.UserService;
import cat.institutmarianao.domain.User;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private UserService userService;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        if (username != null && !username.isEmpty()) {
            User user = userService.findUserByUsername(username);

            if (user != null) {
                userService.remove(user);
            }
        }

        response.sendRedirect(request.getContextPath() + "/UsersServlet");
    }
}
