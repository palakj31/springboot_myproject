package com.itvedant.myproject.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itvedant.myproject.FileStorageProperties;
import com.itvedant.myproject.entities.Product;
import com.itvedant.myproject.repositories.ProductRepositories;


@Service
public class ProductService {

    @Autowired
    private ProductRepositories repository;
    
    private final Path rootLocation;

    public ProductService(FileStorageProperties properties)
    {
        this.rootLocation=Paths.get(properties.getUploadDir());
        try 
            {
                Files.createDirectories(rootLocation);
            }
        catch(IOException e)
            {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"could not create directory to upload");

            }
    }

    public String storeFile(Integer id,MultipartFile file)
    {
        Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()));
        try 
            {
                InputStream inputStream= file.getInputStream();
                Files.copy(inputStream,destinationFile,StandardCopyOption.REPLACE_EXISTING);

            }

        catch(IOException e)
            {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"could not save file");
            }
        
        String uploadedFileUrl=ServletUriComponentsBuilder.fromCurrentContextPath()
                                                            .path("/products/download/")
                                                            .path(file.getOriginalFilename())
                                                            .toUriString();
        Product product =this.repository.findById(id).get();
        product.setId(id);
        product.setImageUrl(uploadedFileUrl);
        this.repository.save(product);
        return "File upload successfully";
    }

    public Resource downloadFile(String filename)
    {
        Path file=rootLocation.resolve(filename);

        try 
            {
                Resource resource = new UrlResource(file.toUri());
                return resource;
            } 
        catch (Exception e) 
            {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Could not download the file");
            }
        
    }
    
}
