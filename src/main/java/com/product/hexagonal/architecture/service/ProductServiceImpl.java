package com.product.hexagonal.architecture.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.hexagonal.architecture.model.Product;
import com.product.hexagonal.architecture.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductServiceUtils productServiceUtils;
	@Autowired
	private ProductRepository productRepository;
	
	private String fileDownloadUri;
	@Override
	public List<Product> getAll() {
		return productRepository.findAll() ;
	}
	@Override
	public Product saveProduct(Product product) {
		product.setImageFile(fileDownloadUri);
		return productRepository.save(product);
	}
	@Override
	public Product getOne(long id) {
		return productRepository.findById(id).get();
	}
	@Override
	public void deleteProductById(long id) {
		productRepository.deleteById(id);
	}

	@Override
	public MultipartFile uploadToLocalFileSystem(MultipartFile imageFile) {
		productServiceUtils.uploadToLocalFileSystem(imageFile);
		fileDownloadUri = productServiceUtils.getFileDownloadUri();
	return imageFile;
	}

	@Override
	public  byte[] getImageWithMediaType(String fileName) {
		return this.productServiceUtils.getImageWithMediaType(fileName);
	}
	@Override
	public void update(Product product ) {
		this.productRepository.save(product);
	}
}
