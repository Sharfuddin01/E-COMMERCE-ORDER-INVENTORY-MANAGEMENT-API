package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
