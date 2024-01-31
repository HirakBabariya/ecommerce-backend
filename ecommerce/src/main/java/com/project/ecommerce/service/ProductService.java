package com.project.ecommerce.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.entities.Image;
import com.project.ecommerce.model.Product;

public interface ProductService {

	public void addNewProduct(Product product, MultipartFile[] files);
	
	public com.project.ecommerce.entities.Product convertTOEntity(Product product);
	
	public List<Product> getAllProducts();
	
	public Product getProductById(Integer productId);
	
	public void deleteProduct(Integer productId);
	
	public Product convertToModel(com.project.ecommerce.entities.Product product);

	public Set<com.project.ecommerce.model.Image> convertToModel(Set<Image> imgs);
}
