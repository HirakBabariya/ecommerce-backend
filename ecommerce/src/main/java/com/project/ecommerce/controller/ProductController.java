package com.project.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
//	@PreAuthorize("hasRole('Admin')")
	@PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public void addNewProduct(@RequestPart("product")Product product, @RequestPart("imageFile") MultipartFile[] files) {
		productService.addNewProduct(product, files);
		
	}
	
	@GetMapping(value = {"/getAllProducts"})
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping(value = {"/getProduct/{id}"})
	public Product getProduct(@PathVariable Integer id) {
		return productService.getProductById(id);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping(value = {"/deleteProduct/{id}"})
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}
}
