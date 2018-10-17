package com.allison.inn.inventorymanagementsystem.business;

import com.allison.inn.inventorymanagementsystem.model.DataOperationResponse;
import com.allison.inn.inventorymanagementsystem.model.Item;
import com.allison.inn.inventorymanagementsystem.model.ItemMetric;
import com.allison.inn.inventorymanagementsystem.services.ItemInventoryService;
import com.allison.inn.inventorymanagementsystem.services.ItemMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryManger {

    @Autowired
    ItemInventoryService itemInventoryService;

    @Autowired
    ItemMetricService itemMetricService;

    /**
     * Function to add items to repository
     * @param items
     * @return
     */
    public DataOperationResponse addItemToInventory(List<Item> items) {

        DataOperationResponse dataOperationResponse = new DataOperationResponse();

            for (Item item : items) {
                this.itemInventoryService.addItem(item);
            }

        return dataOperationResponse;
    }


    /**
     * Function to get item metrics
     * @param itemName
     * @return
     */

    public ItemMetric getItemMetrics(String itemName) {
        return itemMetricService.getItemsMetrics(itemName);
    }
}
