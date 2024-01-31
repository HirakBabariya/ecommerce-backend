package com.project.ecommerce.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.entities.Image;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addNewProduct(Product product, MultipartFile[] files) {
		com.project.ecommerce.entities.Product proEnty = convertTOEntity(product);
		try {
			proEnty.setProductImages(uploadImage(files));
		} catch (IOException e) {
			e.printStackTrace();
		}
		productRepository.save(proEnty);
	}

	@Override
	public com.project.ecommerce.entities.Product convertTOEntity(Product product) {
		com.project.ecommerce.entities.Product proEnty = new com.project.ecommerce.entities.Product();
		BeanUtils.copyProperties(product, proEnty, "productImages");
		return proEnty;
	}

	private Set<Image> uploadImage(MultipartFile[] files) throws IOException {
		Set<Image> images = new HashSet<>();
		for (MultipartFile file : files) {
			Image img = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
			images.add(img);
		}
		return images;
	}

	@Override
	public List<Product> getAllProducts() {
		List<com.project.ecommerce.entities.Product> products = productRepository.findAll();
		List<Product> proModels = new ArrayList<>(products.size());
		products.forEach(product -> proModels.add(convertToModel(product)));
		return proModels;
	}

	@Override
	public Product getProductById(Integer productId) {
		return convertToModel(productRepository.findById(productId).get());
	}

	@Override
	public void deleteProduct(Integer productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public Product convertToModel(com.project.ecommerce.entities.Product product) {
		Product proModel = new Product();
		BeanUtils.copyProperties(product, proModel,"productImages");
		proModel.setProductImages(convertToModel(product.getProductImages()));
		return proModel;
	}
	
	@Override
	public Set<com.project.ecommerce.model.Image> convertToModel(Set<Image> imgs){
		Set<com.project.ecommerce.model.Image> imgModels = new HashSet<com.project.ecommerce.model.Image>(imgs.size());
		imgs.forEach(img -> {
			com.project.ecommerce.model.Image imgModel = new com.project.ecommerce.model.Image();
			BeanUtils.copyProperties(img, imgModel);
			imgModels.add(imgModel);
		});
		return imgModels;
	}

}
