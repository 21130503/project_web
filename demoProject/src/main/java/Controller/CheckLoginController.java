package Controller;

import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Check-login", value = "/check-login")
public class CheckLoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello world");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("User check: " + user);
        if(user == null) {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // Set status to 401
            resp.getWriter().write("{\"redirect\": \"/demoProject_war/login.jsp\"}");
        }

    }
}
