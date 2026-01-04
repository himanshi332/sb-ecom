//package com.ecommerce.project.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.UUID;
//
//@Service
//public class FileServiceImpl implements FileService {
//
//    @Override
//    public String uploadImage(String path, MultipartFile file) throws IOException {
//        // File names of current / original file
//
//        String originalFilename = file.getName();
//
//        // Generate a  unique file name
//        String randomId = UUID.randomUUID().toString();
//        // mat.jpg --> 1234 --> 1234.jpg
//        String fileName = randomId.concat(originalFilename.substring(originalFileName.lastIndexOf('.')));
//        String filePath = path + File.pathSeparator + fileName;
//
//
//        //Check if path exist and create
//        File folder = new File(path);
//        if(!folder.exists())
//            folder.mkdir();
//
//        //Upload to server
//        Files.copy(file.getInputStream () , paths.get(filePath));
//
//        //returning file name
//        return fileName;
//    }
//}



package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // Original file name (example: cat.jpg)
        String originalFilename = file.getOriginalFilename();

        // Generate unique file name
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(
                originalFilename.substring(originalFilename.lastIndexOf('.'))
        );

        // Full file path
        String filePath = path + File.separator + fileName;

        // Create folder if not exists
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Upload file
        Files.copy(file.getInputStream(), Paths.get(filePath));

        // Return saved file name
        return fileName;
    }
}
