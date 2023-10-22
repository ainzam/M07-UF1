package cat.institutmarianao.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Promo2 extends HttpServlet {

        private static final long serialVersionUID = 1L;

        private Set<String> ip;

        @Override
        public void init(ServletConfig config) throws ServletException {
                super.init(config);
                ip = new HashSet<>();
        }

        @Override
        public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

                String requestIp = request.getRemoteAddr();

                if (ip.contains(requestIp)) {

                        // html redirect - rest of times
                        response.sendRedirect("rest_times.html");
                } else {

                        // html redirect - first time
                        response.sendRedirect("first_time.html");
                        ip.add(requestIp);
                }
        }
}
