package com.rogerr.custom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.rogerr.custom.model.Product;



@Service
public class ProductService {

	
	public List<Product> getAllProducts(){
		
	List<Product>listOfProducts = new ArrayList<>();
	listOfProducts.add(new Product("25023","IPHONE X", "This iPhone is Awesome, with a capital A!",499.99,"Alicia Keys"));
	listOfProducts.add(new Product("25024","Samsung Galaxy 22", "This Galaxy 22 is Awesome, with a capital A! ",999.99,"Taylor Russell"));
	listOfProducts.add(new Product("25025","Porsche Design Huawei Mate 10 Pro", "This Mate 10 Pro is Awesome, with a capital A!",599.99,"Tom Cruise"));
	
	return listOfProducts;
	}
	
	public Product getProductById(String id) {
		Predicate<Product> byId = p -> p.getId().equals(id);
		return filterProducts(byId);
	}

	private Product filterProducts(Predicate<Product> byId) {
		
		// filters product by Id using stream function
		return getAllProducts().stream().filter(byId).findFirst().orElse(null);
	}
	
}
