//package Fillter;
//
//import nhom26.User;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(value = "/admin")
//public class FilterAdmin implements javax.servlet.Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//            }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String url = httpServletRequest.getServletPath();
////        System.out.println(url);
//        if(url.endsWith(".jsp") && !url.endsWith("login.jsp") ||!url.endsWith("register.jsp")){
//            httpResponse.sendRedirect(url.substring(0,url.length()-4));
//        }
//        if(userHasPermission(servletRequest)){
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
//        else{
//            httpResponse.sendRedirect("404.jsp");
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//    private boolean userHasPermission(ServletRequest request) {
//       if(request instanceof HttpServletRequest){
//           HttpSession session = ((HttpServletRequest) request).getSession();
//           User user = (User) session.getAttribute("user");
//           if( user == null||!user.isAdmin() ){
//               return  false;
//           }
//       }
//        return true;
//    }
//
//}
