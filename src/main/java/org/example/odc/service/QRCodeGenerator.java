package org.example.odc.service;

public interface QRCodeGenerator {
    String generateQRCodeImage(String text, int width, int height, String filePath);
    byte[] getQRCodeImage(String text, int width, int height);
}
