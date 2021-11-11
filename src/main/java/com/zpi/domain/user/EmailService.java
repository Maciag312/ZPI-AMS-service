package com.zpi.domain.user;

import javax.mail.MessagingException;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageWithImage(String to, String subject, String text, BufferedImage image) throws MessagingException, IOException;
}
