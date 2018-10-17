package com.allison.inn.inventorymanagementsystem.controller;


import com.allison.inn.inventorymanagementsystem.business.InventoryManger;
import com.allison.inn.inventorymanagementsystem.business.InventoryStatusCalculator;
import com.allison.inn.inventorymanagementsystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.allison.inn.inventorymanagementsystem.constants.InventoryManagementSystemConstants.ADD_ITEM_TO_INVENTORY;
import static com.allison.inn.inventorymanagementsystem.constants.InventoryManagementSystemConstants.GET_STATUS_AFTER_DAYS;


@Controller
public class InventoryController {

    /**
     * Class which handles the inverntory status calculations
     */
    @Autowired
    public InventoryStatusCalculator inventoryStatusCalculator;


    /**
     * Inventory manager to add items and fetch item metrics
     */
    @Autowired
    InventoryManger inventoryManger;

    /**
     * API_getStatusAfterDays which fetches the status of inventory for future date
     * @param statusAfterDays
     * @return
     */
    @RequestMapping(value = GET_STATUS_AFTER_DAYS, method = RequestMethod.GET)
    public @ResponseBody
    List<Item> getStatusAfterDays(@RequestParam int statusAfterDays){
        return this.inventoryStatusCalculator.getInventoryStatusAfterDays(statusAfterDays);
    }

    /**
     *
     * @param items
     * @return
     */
    @RequestMapping(value = ADD_ITEM_TO_INVENTORY, method = RequestMethod.POST)
    public @ResponseBody
    DataOperationResponse addItemToInventory(@RequestBody AddItemRequest items){
        return this.inventoryManger.addItemToInventory(items.items);
    }

}
