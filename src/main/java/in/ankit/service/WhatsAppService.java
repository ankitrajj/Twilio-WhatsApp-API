package in.ankit.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import in.ankit.config.TwilioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;

@Service
public class WhatsAppService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public WhatsAppService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    // Send text message
    public String sendWhatsAppMessage(String to, String body) {
        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(twilioConfig.getFromNumber()),
                body
        ).create();

        return "Message sent with SID: " + message.getSid();
    }

    // Send media message (image, pdf, video, etc.)
    public String sendWhatsAppMedia(String to, String body, String mediaUrl) {
        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(twilioConfig.getFromNumber()),
                body
        ).setMediaUrl(Collections.singletonList(URI.create(mediaUrl))) // media file link
        .create();

        return "Media message sent with SID: " + message.getSid();
    }
}
