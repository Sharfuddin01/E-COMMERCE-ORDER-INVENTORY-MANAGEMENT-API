package com.api.service;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.dto.OrderItemDto;
import com.api.entity.Order;
import com.api.entity.OrderItem;
import com.api.entity.Product;
import com.api.enums.OrderStatus;
import com.api.repository.OrderItemRepository;
import com.api.repository.OrderRepository;
import com.api.repository.ProductRepository;

@Service
public class OrderItemService {

	private OrderItemRepository itemRepo;
	private ProductRepository productRepo;
	private OrderRepository orderRepo;

	
	public OrderItemService(OrderItemRepository itemRepo, ProductRepository productRepo,OrderRepository orderRepo) {
		this.itemRepo = itemRepo;
		this.productRepo = productRepo;
		this.orderRepo=orderRepo;
	}

	public OrderItemDto orderItem(int id, int count) {
		
		Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
		 

		      if(count<=0) {
		    	  throw new RuntimeException("product count must be Greater than Zero");
		      }
		      if(product.getStock()<count) {
		    	  throw new RuntimeException("Stock is not Available");
		      }
		      
		            Order order=new Order();
		          
		             order.setAmount(count*product.getPrice());
		             order.setStatus(OrderStatus.PENDING);
		              orderRepo.save(order);
		            
		    	  OrderItem item=new OrderItem();
		    	   item.setProduct(product);
		    	   item.setOrder(order);
		    	   item.setPrice(product.getPrice());
		    	   item.setQuantity(count);
		    	   itemRepo.save(item);
		    	   
		    	   product.setStock(product.getStock()-count);
		    	   productRepo.save(product);
		    	   
		    	 
		    	  OrderItemDto dto=new OrderItemDto();
		    	   dto.setProductId(item.getProduct().getId());	
		    	   dto.setOrderId(item.getOrder().getId());
		    	   BeanUtils.copyProperties(item,dto); 
		           return dto;
			
	}
}
