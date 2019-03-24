package fpt.edu.vn.sfafinal.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {

    @GetMapping
    public ResponseEntity<byte[]> getImage(@RequestParam("name") String imageName) {

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJE3AD47F4T22LZGA",
                "QzZTpDNDXJQ7TKhEJTNZn84sY1/jbihWRV3fOAC1"
        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();
        S3Object s3Object = s3client.getObject("mycloud-s3", imageName);

//        byte[] bytes = getFile(imageName);
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(s3Object.getObjectContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bytes.length == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
    }

//
//    private byte[] getFile(String fileName) {
//        byte[] bytes = new byte[0];
//        String folder = "src/main/resources/static/product-image/";
//        Path path = Paths.get(folder + fileName);
//        try {
//            bytes = Files.readAllBytes(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bytes;
//    }


    @PostMapping
    public ResponseEntity generateImgURL(@RequestParam("file") MultipartFile multipartFile) {

//        String folder = "src/main/resources/static/product-image/";
//        int indexOfSlash = multipartFile.getContentType().indexOf("/");
//        String imgType = multipartFile.getContentType().substring(indexOfSlash + 1);
//        String fileName = "product-" + System.currentTimeMillis() + "." + imgType;

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJE3AD47F4T22LZGA",
                "QzZTpDNDXJQ7TKhEJTNZn84sY1/jbihWRV3fOAC1"
        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();

        String key = generateFileName(multipartFile);
        String imgURL = "https://s3-ap-southeast-1.amazonaws.com/mycloud-s3/" + key;
        try {
            File file = convertMultiPartToFile(multipartFile);
            s3client.putObject(new PutObjectRequest("mycloud-s3", key, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            file.delete();
//            byte[] bytes = multipartFile.getBytes();
//            Path path = Paths.get(folder + fileName);
//            String serverRequest = "http://CloudApi-env-1.4hzzpupupu.ap-southeast-1.elasticbeanstalk.com/image?name=";
//            imgURL = serverRequest + fileName;
//            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(imgURL);
    }

    private String generateFileName(MultipartFile multiPart) {
        return LocalDateTime.now().toString() + "-"
                + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
