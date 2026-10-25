package com.api.dto;

import com.api.entity.Order;
import com.api.entity.OrderItem;
import com.api.enums.OrderStatus;



public class OrderDto {


	private int id;
    private int amount;
    private OrderStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	
}
