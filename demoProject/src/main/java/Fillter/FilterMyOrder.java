package Fillter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(value = "/donhangcuaban")
public class FilterMyOrder implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String url = httpServletRequest.getServletPath();
        UserHasPermission userHasPermission = new UserHasPermission();
        if(userHasPermission.userHasPermission(servletRequest)){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{
            System.out.println("Chuyen huong");
            httpResponse.sendRedirect("login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
