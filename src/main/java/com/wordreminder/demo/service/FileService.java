package com.wordreminder.demo.service;

import com.wordreminder.demo.pojo.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final Path fileStorageLocation; // 文件在本地存储的地址

    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        System.out.println("fileStorageLocation: "+fileStorageLocation);
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = System.currentTimeMillis()+".jpg";
        System.out.println("fileName:  "+fileName);

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                System.out.println("Sorry! Filename contains invalid path sequence");
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return fileName;
    }

    /**
     * 加载文件
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadFileAsResource(String fileName) throws MalformedURLException {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            System.out.println("filePath： "+filePath);
            Resource resource = new UrlResource(filePath.toUri());
            return resource;



    }
}
