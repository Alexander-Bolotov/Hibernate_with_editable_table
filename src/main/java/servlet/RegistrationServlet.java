package servlet;



import model.User;
import service.Service;
import service.ServiceIml;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = ServiceIml.getInstance();

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if (name == null || password == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Не введен пароль или логин");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                if(service.addUser(new User(name, password, role))) {
                    resp.sendRedirect("/admin.jsp");
                } else {

                    req.setAttribute("error", 1);
                    req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}