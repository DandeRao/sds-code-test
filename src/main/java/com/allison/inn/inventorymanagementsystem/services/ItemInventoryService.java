package com.allison.inn.inventorymanagementsystem.services;

import com.allison.inn.inventorymanagementsystem.model.Item;
import com.allison.inn.inventorymanagementsystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemInventoryService {

    @Autowired
    ItemRepository itemRepository;

    /**
     * Function which gets the items in inventory (repository)
     * @return
     */
    public List<Item> getItemsInInventory() {

        List<Item> items = new ArrayList<>();
        // Add the items in repo to the list using method reference
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    /**
     * Function which addd the item to repository
     * @param item Item to be added to repository
     * @throws DataAccessException Exception in case save operation fails
     */
    public void addItem(Item item) {

        // The quality of an item is never negative and The quality of an item is never more than 50
        if(item.quality > 50) {
            item.quality = 50;
        }  else if(item.quality < 0) {
            item.quality = 0;
        }

        itemRepository.save(item);
    }
}
