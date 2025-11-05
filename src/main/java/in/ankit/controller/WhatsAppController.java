package in.ankit.controller;

import in.ankit.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    // Text Message Endpoint
    @PostMapping("/send")
    public String sendTextMessage(@RequestParam String to, @RequestParam String message) {
        return whatsAppService.sendWhatsAppMessage(to, message);
    }

    // Media Message Endpoint
    @PostMapping("/send-media")
    public String sendMediaMessage(@RequestParam String to,
                                   @RequestParam String message,
                                   @RequestParam String mediaUrl) {
        return whatsAppService.sendWhatsAppMedia(to, message, mediaUrl);
    }
}