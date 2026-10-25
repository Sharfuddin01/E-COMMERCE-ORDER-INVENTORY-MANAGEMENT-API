package com.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.dto.ProductDto;
import com.api.dto.ProductResponseDto;
import com.api.entity.Product;
import com.api.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepo;
	
	public ProductService(ProductRepository productRepo) {
		this.productRepo=productRepo;
	}
	
	public ProductDto add(ProductDto productDto) {
		 
		Product p=new Product();
		BeanUtils.copyProperties(productDto,p);
		Product pro=productRepo.save(p);
		BeanUtils.copyProperties(p, productDto);
		
		return productDto;
	}
	
	public ProductResponseDto getAll(int pageNo,int pageSize,String sortBy,String sortDir) {
		
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(Sort.Direction.ASC,sortBy):Sort.by(Sort.Direction.DESC,sortBy);
		
		Pageable pageable=PageRequest.of(pageNo, pageSize, sort);
	     Page <Product> page=productRepo.findAll(pageable);
	     
	     
	      List<Product>product=page.getContent();
	      List<ProductDto> dtos=new ArrayList<>();
	       for(Product p:product) {
	    	   ProductDto productDto=new ProductDto();
	    	   BeanUtils.copyProperties(p, productDto);
	    	   dtos.add(productDto);
	       }
	       
	       ProductResponseDto response=new ProductResponseDto();
	         
	            response.setContent(dtos);
	            response.setFirstPage(page.isFirst());
	            response.setLastPage(page.isLast());
	            response.setPageSise(page.getSize());
	            response.setTotalPage(page.getTotalPages());
	            response.setTotalElement(page.getNumberOfElements());
	            response.setPageNo(page.getNumber()); 
	            
	         return response;
	}
	
	
	
	
	public ProductDto update(ProductDto productDto ,int id) {
		
		Product product=productRepo.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
		
		   product.setPrice(productDto.getPrice());
		   product.setStock(productDto.getStock());
		    productRepo.save(product) ;
		    BeanUtils.copyProperties(product,productDto);
		    
		    return productDto;
	}
	
	  public String delete(int id) {
		  
		  if(!productRepo.existsById(id)) {
			  throw new RuntimeException("Product not Exists");
		  }
		  else {
			  productRepo.deleteById(id);
			  
			  return "deleted";
		  }
		   
	  }
	
	

}
