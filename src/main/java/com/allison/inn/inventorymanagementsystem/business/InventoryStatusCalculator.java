package com.allison.inn.inventorymanagementsystem.business;

import com.allison.inn.inventorymanagementsystem.model.Item;
import com.allison.inn.inventorymanagementsystem.model.ItemDayWiseMetric;
import com.allison.inn.inventorymanagementsystem.model.ItemMetric;
import com.allison.inn.inventorymanagementsystem.services.ItemInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.allison.inn.inventorymanagementsystem.constants.InventoryManagementSystemConstants.CONCERT_BACKSTAGE_PASSES;
import static com.allison.inn.inventorymanagementsystem.constants.InventoryManagementSystemConstants.SULFURAS;

@Component
public class InventoryStatusCalculator {

    /**
     * Inventory Service which provides Inventory details
     */
    @Autowired
    ItemInventoryService itemInventoryService;

    /**
     * Inventory Manager which handles Item addition operations
     */
    @Autowired
    InventoryManger inventoryManger;

    /**
     * Function which calculates and returns the inventory details on the future date
     * @param days number of days in future
     * @return Item List with inventory status
     */
    public List<Item>  getInventoryStatusAfterDays(int days) {
        List<Item> itemList = itemInventoryService.getItemsInInventory();
        List<Item> finalItemList = new ArrayList<>();

        for(Item item: itemList) {
            ItemMetric itemMetric = inventoryManger.getItemMetrics(item.name);
            finalItemList.add(this.itemStatusCalculator(item, itemMetric, days));
        };

        return finalItemList;
    }

    /**
     * Funciton which calculates the item status based on the ItemMetric, Current Inventory status and future date
     * @param item Current item status in inventory
     * @param itemMetric Item metric pertaining to current item (particularly useful to define multiple metrics for a single item)
     * @param futureDate Number of days in future the status is being requested for
     * @return Item status after on the day requested
     */

    private Item itemStatusCalculator(Item item, ItemMetric itemMetric, int futureDate) {


        Item finalItemStatus = item;

        for (int i = 0; i < futureDate ; i++) {
            int sellInDaysRemaining = item.getSellIn() - 1;
            // Find the item metric for the given item, filter the metric based on the day and adjustment bracket
            // to ensure we are using the right metric for special items
            ItemDayWiseMetric itemMetricToBeApplied = itemMetric.itemMetric.stream()
                    .filter(im -> ((sellInDaysRemaining >= im.minDayAdjuster && im.maxDayAdjuster == 0) ||
                                    sellInDaysRemaining >= im.minDayAdjuster && sellInDaysRemaining <= im.maxDayAdjuster ||
                            sellInDaysRemaining < 0 && im.maxDayAdjuster < 0)).findFirst().get();

            // Once the sell by date has passed, quality degrades twice as fast
            int qualitySellInAdjuster =  finalItemStatus.sellIn >= 0 ? 1 : 2;;

            finalItemStatus.sellIn = item.sellIn + itemMetricToBeApplied.sellInAdjuster;
            finalItemStatus.quality = (item.quality + (itemMetricToBeApplied.qualityAdjuster*qualitySellInAdjuster)) > 0 ?
                    (item.quality + (itemMetricToBeApplied.qualityAdjuster*qualitySellInAdjuster)): 0;


        }
        return finalItemStatus;
    }

}
