package com.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.OrderDto;
import com.api.dto.ResponseAPI;
import com.api.enums.OrderStatus;
import com.api.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}

	  @PatchMapping("/update")
	  public ResponseEntity<ResponseAPI<OrderDto>> UpdateOrder(@RequestParam int id,@RequestParam OrderStatus status) {
		  
		  OrderDto order =orderService.update(id, status);
		  
		  ResponseAPI<OrderDto> response =new ResponseAPI<>();
		  
		  response.setData(order);
		  response.setMessage("Updated");
		  response.setStatus(200);
		  
		  return new ResponseEntity<>(response, HttpStatus.OK);
	  }
	
}
