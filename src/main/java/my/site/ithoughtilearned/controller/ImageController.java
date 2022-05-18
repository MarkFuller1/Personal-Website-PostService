package my.site.ithoughtilearned.controller;

import lombok.extern.slf4j.Slf4j;
import my.site.ithoughtilearned.Utility;
import my.site.ithoughtilearned.model.Image;
import my.site.ithoughtilearned.model.dto.ImageUploadResponse;
import my.site.ithoughtilearned.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/images")
@CrossOrigin(originPatterns = "*/**")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file, @RequestHeader Map<String, String> headers) throws IOException {
        Image saved = imageRepository.save(Image.builder().name(file.getOriginalFilename()).type(file.getContentType()).image(Utility.compressImage(file.getBytes())).build());
        String theirUrl = "http://" + headers.get("host") + "/images/" + saved.getId().toString();
        log.info(theirUrl);
        return ResponseEntity.status(HttpStatus.OK).body(new ImageUploadResponse(saved.getId(), theirUrl));
    }

    @GetMapping(path = {"/{id}/info"})
    public Image getImageDetails(@PathVariable("id") String id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(UUID.fromString(id));

        return Image.builder().name(dbImage.get().getName()).type(dbImage.get().getType()).image(Utility.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(UUID.fromString(id));

        return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getType())).body(Utility.decompressImage(dbImage.get().getImage()));
    }
}
