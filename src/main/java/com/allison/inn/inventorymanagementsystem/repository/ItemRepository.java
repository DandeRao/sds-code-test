package com.allison.inn.inventorymanagementsystem.repository;


import com.allison.inn.inventorymanagementsystem.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {

}
