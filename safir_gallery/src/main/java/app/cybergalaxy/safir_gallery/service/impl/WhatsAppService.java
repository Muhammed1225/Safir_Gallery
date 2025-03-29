package app.cybergalaxy.safir_gallery.service.impl;

import app.cybergalaxy.safir_gallery.exception.MyException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class WhatsAppService {

    private String accountSid = System.getenv("ACCOUNT_SID");

    private String authToken = System.getenv("AUTH_TOKEN");

    @Value("${twilio.whatsapp.number}")
    private String twilioWhatsAppNumber;

    public void sendWhatsAppMessage(String messageBody, String mediaUrl) {
        try {
            Twilio.init(accountSid, authToken);

            Message message = Message.creator(
                    new PhoneNumber("whatsapp:+994775087856"),
                    new PhoneNumber(twilioWhatsAppNumber),
                    messageBody
            ).setMediaUrl(List.of(URI.create(mediaUrl))).create();
        } catch (Exception e) {
            throw new MyException("Error sending media message: " + e.getMessage());
        }
    }
}
