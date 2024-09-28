package org.example.odc.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.example.odc.service.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class QRCodeGeneratorImpl implements QRCodeGenerator {

    @Value("${file.upload-dir}")
    private String baseStoragePath;

    @Override
    public String generateQRCodeImage(String text, int width, int height, String folder) {
        try{
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filePath =  this.baseStoragePath+"/"+folder+"/QrCode_" + timestamp + ".png";

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            return filePath;
        }catch (WriterException | IOException e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public byte[] getQRCodeImage(String text, int width, int height) {
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageConfig con = new MatrixToImageConfig( 0xFF000002 , 0xFFFFC041 ) ;

            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,con);
            byte[] pngData = pngOutputStream.toByteArray();
            return pngData;
        }catch (WriterException | IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
