package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name ="DiscountClientController" ,value = "/discountClient")
public class DiscountClientController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("count") == null){
            session.setAttribute("count", 3);
        }
        int count = (int) session.getAttribute("count");
        session.setAttribute("count", count-1);

        req.getRequestDispatcher("discountClient.jsp").forward(req,resp);
    }
}
