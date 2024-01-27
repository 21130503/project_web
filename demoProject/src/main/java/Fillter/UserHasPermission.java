package Fillter;

import nhom26.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserHasPermission {
    public boolean userHasPermission(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            HttpSession session = ((HttpServletRequest) request).getSession();
            User user = (User) session.getAttribute("user");
            if (user == null || !user.isAdmin()) {
                return false;
            }
        }
        return true;

    }
}
