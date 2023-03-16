package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${file.upload-dir}")
    private String image_folder;

    @Override
    public String saveImageToFileSystem(MultipartFile image) throws IOException {
        String resultFileName = UUID.randomUUID() + "." + image.getOriginalFilename();
        String resultUrl = image_folder + resultFileName;
        image.transferTo(new File(resultUrl));
        return "/static/images/" + resultFileName;
        /*Resource resource = resourceLoader.getResource("classpath:" + image_folder);
        Path uploadPath = Paths.get(resource.getURI());

        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String resultFileName = UUID.randomUUID() + "." + image.getOriginalFilename();
        String resultUrl = image_folder + resultFileName;
//        image.transferTo(new File(resultUrl));
        try(InputStream inputStream = image.getInputStream()) {
            Files.copy(inputStream, uploadPath.resolve(resultFileName),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        return resultUrl;*/
    }
}
