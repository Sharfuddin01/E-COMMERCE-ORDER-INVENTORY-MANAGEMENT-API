package com.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.dto.OrderDto;
import com.api.entity.Order;
import com.api.entity.OrderItem;
import com.api.entity.Product;
import com.api.enums.OrderStatus;
import com.api.repository.OrderItemRepository;
import com.api.repository.OrderRepository;
import com.api.repository.ProductRepository;

@Service
public class OrderService {
	
	private OrderRepository orderRepo;
	private OrderItemRepository orderItemRepo;
	private ProductRepository productRepo;
	
	public OrderService(OrderRepository orderRepo,OrderItemRepository orderItemRepo,ProductRepository productRepo) {
		     this.orderRepo=orderRepo;
		     this.orderItemRepo=orderItemRepo;
		     this.productRepo=productRepo;
	}
	
	  public OrderDto  update(int id ,OrderStatus status) {
		   
		  Order order=orderRepo.findById(id).orElseThrow(()->new RuntimeException("OrderItem not present"));
                   OrderDto orderDto=new OrderDto();
     
		         if(status==OrderStatus.CANCELLED) {
		        	 OrderItem orderItem =order.getOrderItem();
                      Product product=orderItem.getProduct();
                        product.setStock(product.getStock()+orderItem.getQuantity());
                        productRepo.save(product);
                          
                          order.setStatus(status);
                          orderRepo.save(order);
      		         BeanUtils.copyProperties(order, orderDto);
                        return orderDto;
		         }
		  
		         else {
		          order.setStatus(status);
		           orderRepo.save(order);
		            BeanUtils.copyProperties(order, orderDto);
		           return orderDto;
		         }
	  }

}
