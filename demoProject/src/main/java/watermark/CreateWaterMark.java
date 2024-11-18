package watermark;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CreateWaterMark {
    public File createFolderWaterMark(HttpServletRequest request){
        File folderUpload = new File(request.getServletContext().getRealPath("/imagesWatermark/"));

        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        folderUpload.setWritable(true, false);
        System.out.println("Link " + folderUpload);
        return folderUpload;

    }
    public static void addTextWatermark(String text, File sourceImageFile, File destImageFile) throws Exception {
        BufferedImage sourceImage = ImageIO.read(sourceImageFile);
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // Thiết lập thuộc tính cho watermark
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f); // Độ trong suốt
        g2d.setComposite(alphaChannel);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(new Font("Arial", Font.BOLD, 64)); // Font watermark

        // Vị trí watermark (chỉnh sửa theo ý muốn)
        int centerX = sourceImage.getWidth() / 5;
        int centerY = sourceImage.getHeight() / 5;

        // Vẽ watermark lên ảnh
        g2d.drawString(text, centerX, centerY);

        // Lưu ảnh sau khi thêm watermark
        ImageIO.write(sourceImage, "png", destImageFile);

        // Dọn dẹp tài nguyên
        g2d.dispose();
    }
}
