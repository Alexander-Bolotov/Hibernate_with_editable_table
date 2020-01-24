package servlet;


import service.Service;
import service.ServiceIml;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = ServiceIml.getInstance();
        Long id = Long.parseLong(req.getParameter("id"));
        if (id == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Не введен пользователь");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                service.deleteUserById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Service service = ServiceIml.getInstance();
        try {
            service.deleteUserById(id);
            resp.sendRedirect("/admin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}