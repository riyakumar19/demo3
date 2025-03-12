package core.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Random;

@RestController
@RequestMapping("/scan")
public class ScanController {

    @GetMapping
    public String scanQRCode(@RequestParam String email) {
        String mockString = generateMockString();

        // Redirect user to another page (e.g., frontend page)
        //String redirectUrl = "https://yourdomain.com/redirect-page?code=" + mockString;
        //return new RedirectView(redirectUrl);
        String ans ="Payment successfull for mobile no." + mockString;
        return ans;
    }

    private String generateMockString() {
        int length = 10;
        String characters = "1234567890";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }
}


