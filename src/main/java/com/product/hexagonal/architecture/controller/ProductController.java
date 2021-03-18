package com.product.hexagonal.architecture.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.product.hexagonal.architecture.model.Product;
import com.product.hexagonal.architecture.service.ProductService;

@RestController
@RequestMapping(path = "/api")
public class ProductController {
	@Autowired
	private ProductService productService ;

	
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public    void uploadImage(@RequestParam MultipartFile imageFile)throws IOException {
    	productService.uploadToLocalFileSystem(imageFile);
    }
    
    @RequestMapping(value = "/upload/product", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product) {
    	return productService.saveProduct(product);
    }
    
    @RequestMapping(value = "/allproduct", method = RequestMethod.GET)
    public List<Product> getAll() {
    	return productService.getAll();
    }
    @RequestMapping(value = "/getImage/{imageName:.+}",
    				method = RequestMethod.GET	,
    				produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE }
    )
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable(name = "imageName") String fileName) throws IOException {
        return productService.getImageWithMediaType(fileName);
    }
	@RequestMapping(value = "/delete/product/{id}", method = RequestMethod.DELETE)
	@ResponseBody public ResponseEntity<Product> deleteProduct(@PathVariable long id){
		Product product = productService.getOne(id);
		productService.deleteProductById(id);
		return ResponseEntity.ok(product);
	}
	@RequestMapping(value = "/update/product/{id}", method = RequestMethod.PUT)
	@ResponseBody public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product ) {
		productService.update(product);
	    return ResponseEntity.ok(product);
	}
}
