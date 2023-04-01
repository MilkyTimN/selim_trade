package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${file.upload-dir}")
    private String image_folder;

    private static final float COMPRESSION_QUALITY = 0.6f; // Set compression quality to 60%

    @Override
    public String saveImageToFileSystem(MultipartFile image) throws IOException {
        String resultFileName = UUID.randomUUID() + "." + image.getOriginalFilename();
        String resultUrl = image_folder + resultFileName;

        // Read the uploaded image into a BufferedImage
        BufferedImage originalImage = ImageIO.read(image.getInputStream());

        // Compress the image
        BufferedImage compressedImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                originalImage.getType());
        compressedImage.getGraphics().drawImage(originalImage, 0, 0, null);
        File compressedFile = new File(resultUrl);

        // Get the format name of the image
        String formatName = resultFileName.substring(resultFileName.lastIndexOf(".") + 1).toLowerCase();

        // Compress the image and write it to the file system
        ImageWriter writer = ImageIO.getImageWritersByFormatName(formatName).next();
        writer.setOutput(ImageIO.createImageOutputStream(compressedFile));
        ImageWriteParam params = writer.getDefaultWriteParam();
        params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        params.setCompressionQuality(COMPRESSION_QUALITY);
        writer.write(null, new IIOImage(compressedImage, null, null), params);

        return "/static/images/" + resultFileName;
    }
}


