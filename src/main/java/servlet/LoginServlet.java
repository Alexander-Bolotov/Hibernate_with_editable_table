package servlet;

import model.User;
import service.Service;
import service.ServiceIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = ServiceIml.getInstance();
        final String name = req.getParameter("name");
        final String password = req.getParameter("password");
        String role = null;
        try {
            role = service.getRoleByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HttpSession httpSession = req.getSession();
        if(name==null || password==null){
            resp.sendRedirect("index.jsp");
        }
        if (httpSession.getAttribute("role") == null) {
            try {
                if (service.nameIsExist(name)) {
                    req.getSession().setAttribute("name", name);
                    req.getSession().setAttribute("password", password);
                    req.getSession().setAttribute("role", role);
                    req.getRequestDispatcher("/login").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (httpSession.getAttribute("role").equals("Admin")){
            resp.sendRedirect("admin.jsp");
        } else {
            resp.sendRedirect("user.jsp");
        }
    }
}