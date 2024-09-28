package org.example.odc.service;

public interface SendMail {
    void send(String to, String subject, String content);
}
