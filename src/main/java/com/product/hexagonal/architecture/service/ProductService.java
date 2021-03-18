package com.product.hexagonal.architecture.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.product.hexagonal.architecture.model.Product;

public interface ProductService {

	List<Product> getAll();

	Product saveProduct(Product product);

	Product getOne(long id);

	void deleteProductById(long id);
	
	void update(Product product);

	MultipartFile uploadToLocalFileSystem(MultipartFile imageFile);

	byte[] getImageWithMediaType(String fileName);
}
