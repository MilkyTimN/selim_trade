package kg.megalab.selim_trade.controller;

import jakarta.servlet.ServletContext;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
//import org.apache.tomcat.util.http.fileupload.FileUtils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.module.ResolutionException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {

    @Value("${file.upload-dir}")
    private String pathOfImageFolder;

    @GetMapping("/{image-name}")
    public ResponseEntity<byte[]> getImageAdResource(@PathVariable("image-name") String imageName ) {
        long start = System.nanoTime();
        MediaType contentType = imageName.substring(imageName.lastIndexOf(".")+1).equals("png") ?
                MediaType.IMAGE_PNG
                :MediaType.IMAGE_JPEG;

        byte[] image = new byte[0];

//        try {
//            image = FileUtils.readFileToByteArray(new File(pathOfImageFolder + imageName));
//        } catch (IOException e) {
//            throw new ResolutionException("Image not found!");
//        }
        try {
            image = Files.readAllBytes(new File("/home/team4/uploads/" + imageName).toPath());
        } catch (IOException e) {
            throw new ResourceNotFoundException("Image not found!");
        }

        return ResponseEntity.ok().contentType(contentType).body(image);
    }
}
