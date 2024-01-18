package Controller;

import DAO.ProductDAO;
import DAO.TopicDAO;
import nhom26.Album;
import nhom26.OddImage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShopController", value = "/shop")

public class ShopController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isFiltering = false;

        //Kiểm tra xem có đang lọc ko
        if (req.getParameter("filterShop") != null) {
            isFiltering = true;
        }

        //Lấy giá trị lọc ra để gửi qa yêu cầu Phân trang
        String priceRange = req.getParameter("priceRange");
        if (priceRange == null) {
            priceRange = "all";
        }
        req.setAttribute("selectedPriceRange", priceRange);


        if (isFiltering) {
            doPost(req, resp);
        } else {
            // Xử lý hiển thị thông thường <Code Cũ>
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            ProductDAO productDAO = new ProductDAO();
            TopicDAO topicDAO = new TopicDAO();
            int recSize = 6;
            int page = 1;
            int totalOdd = productDAO.totalOdd();
            int totalAlbum = productDAO.totalAlbum();
            int totalPage = (int) Math.ceil((totalAlbum + totalOdd) / recSize);
            String type = req.getParameter("type");
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            System.out.println(type + " " + page);
            if ("album".equals(type)) {
                req.setAttribute("listAlbum", productDAO.getAllAlbumForClient(page, recSize));
                req.getRequestDispatcher("shop.jsp").forward(req, resp);
                return;
            }
            if ("odd".equals(type)) {
                req.setAttribute("listOddImage", productDAO.getAllOddImageForClient(page, recSize));
                req.getRequestDispatcher("shop.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("listOddImage", productDAO.getAllOddImageForClient(page, recSize));
            req.setAttribute("listAlbum", productDAO.getAllAlbumForClient(page, recSize));
            req.setAttribute("listTopic", topicDAO.getAllTopicsForClient());
            req.setAttribute("totalPage", totalPage);
            req.setAttribute("currentPage", page);

            req.getRequestDispatcher("shop.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            String priceRange = request.getParameter("priceRange");
            int minPrice = 0;
            int maxPrice = Integer.MAX_VALUE;

            //Nhân với 1000 để lên tiền VNĐ
            if (priceRange != null && !priceRange.equals("all")) {
                String[] prices = priceRange.split("-");
                minPrice = Integer.parseInt(prices[0]) * 1000;
                maxPrice = Integer.parseInt(prices[1]) * 1000;
            }

            //Lấy thông tin số trang hiện tại
            String pageStr = request.getParameter("page");
            String recSizeStr = request.getParameter("recSize");

            //Xét số sản phẩm được phép hiển thị ở 1 trang
            int page = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
            int recSize = (recSizeStr != null) ? Integer.parseInt(recSizeStr) : 6;

            ProductDAO productDAO = new ProductDAO();

            // Lấy danh sách sản phẩm sau khi lọc
            List<Album> filteredAlbums = productDAO.getFilteredAlbums(page, recSize, minPrice, maxPrice);
            List<OddImage> filteredOddImages = productDAO.getFilteredOddImages(page, recSize, minPrice, maxPrice);

            // Tính tổng số trang dựa trên kết quả lọc
            int totalFilteredItems = productDAO.totalFilteredItems(minPrice, maxPrice);
            int totalPage = (int) Math.ceil((double) totalFilteredItems / recSize);

            // Set các thuộc tính cho request
            request.setAttribute("filteredAlbums", filteredAlbums);
            request.setAttribute("filteredOddImages", filteredOddImages);
            request.setAttribute("selectedPriceRange", priceRange);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", page);

            RequestDispatcher dispatcher = request.getRequestDispatcher("shop.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
