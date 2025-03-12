package core.demo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;
@Service
public class QrCodeGenerator<BitMatrix> {
    private static final String QR_CODE_DIRECTORY = "qr_codes/";
    public String generateQRCode(String email) throws Exception{
        String qrData = "https://yourdomain.com/scan?email=" + email;

        String fileName = email.replaceAll("[^a-zA-Z0-9]", "_") + ".png"; // Safe file name
        String filePath = QR_CODE_DIRECTORY + fileName;

        // Create directory if it doesn't exist
        File directory = new File(QR_CODE_DIRECTORY);
        if (!directory.exists()) {
            boolean created = directory.mkdirs(); // Create if it does not exist
            if (created) {
                System.out.println("✅ Directory created: " + directory.getAbsolutePath());
            } else {
                System.out.println("❌ Failed to create directory!");
                return null;
            }
        }
        com.google.zxing.common.BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, 300, 300);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

        // Save image to server
        File outputFile = new File(filePath);
        ImageIO.write(image, "png", outputFile);

        // ✅ Check if file was created
        if (!outputFile.exists()) {
            System.out.println("❌ ERROR: QR Code file was NOT created!");
            return null;
        }

        System.out.println("✅ QR Code saved at: " + outputFile.getAbsolutePath());

        // Return URL of the generated QR code
        return "http://localhost:8082/qr/images/" + fileName;
    }

}
