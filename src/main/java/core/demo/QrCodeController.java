package core.demo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    @GetMapping(produces = "image/png")
    public byte[] generateQRCode(@RequestParam String email) throws WriterException, IOException {
        // The link that will be shown when the QR is scanned
        String qrText = "https://localhost:8082/scan-link?email=" + email;
        int width = 250;
        int height = 250;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        return baos.toByteArray();
    }
}

//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import org.springframework.web.bind.annotation.*;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/qrcode")
//public class QrCodeController {
//
//    @GetMapping(produces = "image/png")
//    public byte[] generateQRCode(@RequestParam String email) throws WriterException, IOException {
//        // The URL that the QR code will point to (Your scan API)
//        String qrText = "http://localhost:8082/scan?email=" + email;
//        int width = 250;
//        int height = 250;
//
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        Map<EncodeHintType, Object> hints = new HashMap<>();
//        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//
//        BitMatrix bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, width, height, hints);
//        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(qrImage, "png", baos);
//        return baos.toByteArray();
//    }
//}


//package core.demo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/qr")
//
//public class QrCodeController {
//    @Autowired
//    private final QrCodeGenerator qrCodeGenerator;
//    public QrCodeController(QrCodeGenerator qrCodeGenerator){
//        this.qrCodeGenerator = qrCodeGenerator;
//    }
//    @GetMapping("/generate")
//    public String generateCode(@RequestParam String email){
//        try{
//            return qrCodeGenerator.generateQRCode(email);
//        }
//        catch(Exception e){
//            return "Error generating QR. Please try again in Sometime ;)";
//        }
//    }
//
//
//
//}

