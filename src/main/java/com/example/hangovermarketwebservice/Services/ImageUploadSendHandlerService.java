package com.example.hangovermarketwebservice.Services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;


@Service
public class ImageUploadSendHandlerService {
    
    public void DecodeAndSave(String base64Img, String path) throws IOException {

        byte[] decoded = Base64.getDecoder().decode(base64Img);
        ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
        var image = ImageIO.read(bis);
        bis.close();
        File saved = new File(path);
        ImageIO.write(image, "png", saved);
    }
}
