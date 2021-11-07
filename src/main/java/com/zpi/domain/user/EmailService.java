package com.zpi.domain.user;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
