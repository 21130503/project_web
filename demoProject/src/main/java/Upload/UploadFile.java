package Upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;

public class UploadFile {
    public File getFolderUpload() {
       File folderUpload = new File("D:/LTW_TL/project_web/demoProject/src/main/webapp" + "/images");
//        File folderUpload = new File(request.getServletContext().getRealPath("/images/"));
        if (!folderUpload.exists()) {

            folderUpload.mkdirs();
        }
        System.out.println("Link " + folderUpload);
        return folderUpload;
    }
     public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
