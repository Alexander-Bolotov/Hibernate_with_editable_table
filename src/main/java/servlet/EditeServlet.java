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

@WebServlet("/edit")
public class EditeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = ServiceIml.getInstance();

        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        User user = new User(id, name, password, role);

        if (name == null  || password == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Не введен пароль или логин или ID");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                service.editeUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/admin.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        Service service = ServiceIml.getInstance();
        req.setAttribute("id", id);
        req.setAttribute("name", name);
        req.setAttribute("password", password);
        req.setAttribute("role", role);
        try {
            User user = service.getUserById(id);
            req.getSession().setAttribute("user", user);
            RequestDispatcher requestDispatcher =req.getRequestDispatcher("/edit.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}