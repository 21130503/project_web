package Controller;

import DAO.ProductDAO;
import DAO.TopicDAO;
import com.mysql.cj.xdevapi.JsonValue;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController",value = "/product/*")
public class ProductController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String type = req.getPathInfo();
        System.out.println(type);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        if(type.equals("/addImg")){
            while((line = bufferedReader.readLine()) !=null){
                stringBuilder.append(line);
            }
            bufferedReader.close();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            String nameTopic = jsonObject.getString("nameTopic");
            String nameImg = jsonObject.getString("nameImg");
            String description =  jsonObject.getString("description");
            int price = jsonObject.getInt("price");
            int discount = jsonObject.getInt("discount");
            String source = jsonObject.getString("source");
            TopicDAO topicDAO = new TopicDAO();
            ProductDAO productDAO = new ProductDAO();
            JSONObject jsonObjectResults = new JSONObject();
            if(!topicDAO.checkNameTopicExist(nameTopic)){
                jsonObjectResults.put("status", 500);
                jsonObjectResults.put("message", "Tên chủ đề không tồn taị");
                resp.setContentType("application/json");
                resp.getWriter().write(jsonObjectResults.toString());
            }
            else{
                if(productDAO.insertOddImage(nameImg,source,description,price,discount,nameTopic)){
                    jsonObjectResults.put("status", 200);
                    jsonObjectResults.put("message", "Thêm sản phẩm thành công");
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonObjectResults.toString());
                }
                else{
                    jsonObjectResults.put("status", 500);
                    jsonObjectResults.put("message", "Thêm sản phẩm thất bại");
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonObjectResults.toString());
                }
            }

        }
        if(type.equals("/addAlbum")){
            while((line = bufferedReader.readLine()) !=null){
                stringBuilder.append(line);
            }
            bufferedReader.close();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            String nameTopic = jsonObject.getString("nameTopic");
            String nameAlbum= jsonObject.getString("nameAbum");
            String descriptionAlbum =  jsonObject.getString("descriptionAlbum");
            int price = jsonObject.getInt("price");
            int discount = jsonObject.getInt("discount");
            JSONArray listBase64 = jsonObject.getJSONArray("source");
            List<String> list = new ArrayList<>();
            for(Object jsonValue : listBase64){
                String jString = jsonValue.toString();
                list.add(jString);
            }
            System.out.println(jsonObject);
        }
    }
}
