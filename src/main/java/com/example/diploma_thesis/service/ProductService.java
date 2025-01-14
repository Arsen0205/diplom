package com.example.diploma_thesis.service;


import com.example.diploma_thesis.dto.request.AddProductDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.Image;
import com.example.diploma_thesis.models.Product;
import com.example.diploma_thesis.models.Supplier;
import com.example.diploma_thesis.repository.ImageRepository;
import com.example.diploma_thesis.repository.ProductRepository;
import com.example.diploma_thesis.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final SupplierRepository supplierRepository;

    public Response addProduct(AddProductDtoRequest request) throws IOException {
        Supplier supplier = supplierRepository.findByLogin(request.getLogin()).orElseThrow(()-> new IllegalArgumentException("Пользователя с таким логином не существует"));

        Product product = new Product();

        product.setCity(request.getCity());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setTitle(request.getTitle());
        product.setSupplier(supplier);

        List<Image> images = new ArrayList<>();
        for(int i=0; i < request.getImages().size(); i++){
            MultipartFile file = request.getImages().get(i);
            Image image = toImageEntity(file);
            image.setProduct(product);
            if(i == 0){
                image.setPreviewImage(true);
            }
            images.add(image);
        }

        productRepository.save(product);
        imageRepository.saveAll(images);

        Long previewImageId = images.stream()
                .filter(Image::isPreviewImage)
                .findFirst()
                .map(Image::getId)
                .orElse(null);

        product.setPreviewImageId(previewImageId);
        productRepository.save(product);

        return new Response("Вы успешно добавили товар");
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
