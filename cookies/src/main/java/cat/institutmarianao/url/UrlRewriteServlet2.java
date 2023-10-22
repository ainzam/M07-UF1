package cat.institutmarianao.url;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UrlRewriteServlet2", urlPatterns = { "/UrlRewriteServlet2" })
public class UrlRewriteServlet2 extends HttpServlet {

        private static final long serialVersionUID = 1L;

        /**
         * Processes requests for both HTTP GET and POST methods.
         *
         * @param request  servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException      if an I/O error occurs
         */
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                        throws IOException {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>URL Servlet 2</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Access to the field sent through the url</h1>");
                        String username = request.getParameter("name");
                        out.print("Hello " + username + "!");
                        out.println("</body>");
                        out.println("</html>");
                }
        }

        /**
         * Handles the HTTP GET method.
         *
         * @param request  servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException      if an I/O error occurs
         */
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws IOException {
                processRequest(request, response);
        }

        /**
         * Handles the HTTP POST method.
         *
         * @param request  servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException      if an I/O error occurs
         */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws IOException {
                processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo() {
                return "Short description";
        }
}