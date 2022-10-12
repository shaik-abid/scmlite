package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.document.ShipmentTransactions;

@Transactional
public interface ShipmentTransactionsRepository 
       extends MongoRepository<ShipmentTransactions, String>{
	
	@Query("{username:'?0'}")
	List<ShipmentTransactions> findByusername(String username);
	
	

}
