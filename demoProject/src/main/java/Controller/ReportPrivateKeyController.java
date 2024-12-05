package Controller;

import DAO.ReportDAO;
import nhom26.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Report Key", value = "/reportKey")
public class ReportPrivateKeyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("Time: "  + time);
        System.out.println("Date: "  + date);
        ReportDAO reportDAO = new ReportDAO();
        if(user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        if(date == null) {
            req.setAttribute("err", "Vui lòng nhập ngày mất");
            resp.sendRedirect("reportKey.jsp");
            return;
        }
        if(time == null) {
            req.setAttribute("err", "Vui lòng giờ ngày mất");
            resp.sendRedirect("reportKey.jsp");
            return;
        }

        if (reportDAO.insertReport(user.getId(),date, time) == true){
            resp.sendRedirect("/demoProject_war/index");
        }
    }
}
