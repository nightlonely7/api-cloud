package fpt.edu.vn.sfafinal.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/image")
public class ImageController {

    @GetMapping
    public ResponseEntity<byte[]> getImage(@RequestParam("name") String imageName) {
        byte[] bytes = getFile(imageName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
    }

    private byte[] getFile(String fileName) {
        byte[] bytes = new byte[0];
        String folder = "src/main/resources/static/product-image/";
        Path path = Paths.get(folder + fileName);
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
