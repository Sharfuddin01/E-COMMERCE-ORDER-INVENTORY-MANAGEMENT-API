package com.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.OrderItemDto;
import com.api.dto.ResponseAPI;
import com.api.service.OrderItemService;


@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
	
	private OrderItemService orderItemService;
	
	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService=orderItemService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<ResponseAPI<OrderItemDto>> order(@RequestParam int id ,@RequestParam int count) {
		
		  OrderItemDto orderItem=orderItemService.orderItem(id,count);
		  
		  ResponseAPI<OrderItemDto> response=new ResponseAPI<>();
		  
		  if(orderItem!=null) {
		  response.setData(orderItem);
		  response.setMessage("Order Confirm");
		  response.setStatus(201);
		  return new ResponseEntity<>(response,HttpStatus.OK);
		  }
		  else {
			  response.setData(orderItem);
			  response.setMessage("Out of Stock");
			  response.setStatus(409);
			  return new ResponseEntity<>(response,HttpStatus.CONFLICT);
		  }
	  }

}
