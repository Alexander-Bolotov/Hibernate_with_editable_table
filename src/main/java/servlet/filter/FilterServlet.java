package servlet.filter;

import model.User;
import service.Service;
import service.ServiceIml;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebFilter(urlPatterns ={"/admin.jsp", "/user.jsp"})
    public class FilterServlet implements Filter {
    private FilterConfig filterConfig = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String act = filterConfig.getInitParameter("active");
        if (act != null) {
            active = (act.toUpperCase().equals("TRUE"));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        final HttpSession httpSession = httpServletRequest.getSession();

        if (httpSession != null && httpSession.getAttribute("role") != null) {//залогирован ли пользователь?
            if (httpSession.getAttribute("role").equals("Admin")) { //если админ
                Service service = ServiceIml.getInstance();
                try {
                    servletRequest.setAttribute("listUser", service.getAllUsers());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                filterChain.doFilter(servletRequest, servletResponse);
            } else {                                                   //если юзер
                httpServletRequest.setAttribute("message", "Ваша роль не позволяет зайти сюда, вернитесь обратно");
                httpServletRequest.getRequestDispatcher("user.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } else {                                                                     //Если не залогирован
            httpServletResponse.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}