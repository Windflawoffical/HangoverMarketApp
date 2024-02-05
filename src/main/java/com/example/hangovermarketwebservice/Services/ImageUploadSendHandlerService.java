package com.example.hangovermarketwebservice.Services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public String EncodeImage(String path) throws IOException{
        try {
         byte[] byteData = Files.readAllBytes(Paths.get(path));     
        return Base64.getEncoder().encodeToString(byteData);   
        } catch (IOException e) {
            byte[] byteData = Files.readAllBytes(Paths.get("./src/main/resources/static/images/vodka.png"));
            return Base64.getEncoder().encodeToString(byteData);
        }
    }
}
