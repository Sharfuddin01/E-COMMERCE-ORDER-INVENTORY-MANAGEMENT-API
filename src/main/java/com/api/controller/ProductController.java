package com.api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ProductDto;
import com.api.dto.ProductResponseDto;
import com.api.dto.ResponseAPI;
import com.api.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	 
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<ResponseAPI<ProductDto>> addProduct(@RequestBody ProductDto productDto) {
		
		ProductDto dto=productService.add(productDto);
		
		
		ResponseAPI<ProductDto> response=new ResponseAPI<>();
		   response.setData(dto);
		   response.setStatus(201);
		   response.setMessage("Created");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	
	    @GetMapping("/all")
	   public ResponseEntity<ResponseAPI<ProductResponseDto>> allProduct(
			  @RequestParam(defaultValue="0",required=false)int pageNo,
			  @RequestParam(defaultValue="2" ,required=false)int pageSize,
			  @RequestParam(defaultValue="id",required=false)String sortBy,
			  @RequestParam(defaultValue="asc",required=false)String sortDir
			   ) {
		   ProductResponseDto response= productService.getAll(pageNo,pageSize,sortBy,sortDir);
		   
		   ResponseAPI<ProductResponseDto> resp=new ResponseAPI<>() ;
		     resp.setData(response);
		     resp.setStatus(200);
		     resp.setMessage("FetchData");
		  
		     return new ResponseEntity<>(resp,HttpStatus.OK);
	   }
	
	
	    @PutMapping("/update")
	   public ResponseEntity<ResponseAPI<ProductDto>> updateProduct(@RequestBody ProductDto productDto ,@RequestParam int id) {
		   
		   ProductDto dto=productService.update(productDto ,id);
		   ResponseAPI<ProductDto> response=new ResponseAPI<>();
		   
		   response.setData(dto);
		   response.setMessage("Updated product");
		   response.setStatus(201);
		   
		   return new ResponseEntity<>(response,HttpStatus.OK);   
	   }
	    
	    @DeleteMapping("/{id}")
		public ResponseEntity<ResponseAPI<String>> deleteProduct(@PathVariable int id) {
			  
	    	String s=productService.delete(id);
			ResponseAPI<String> response =new ResponseAPI<>();

	    	if(s!=null) {
			response.setData(s);
			response.setStatus(200);
			response.setMessage("Execute Successfully");
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		  }
	    	else {
	    		  response.setData("Somthing went wrong");
	    		  response.setStatus(400);
	    		  response.setMessage("Error Found");
	    		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	        
       }
}