package com.allison.inn.inventorymanagementsystem.services;

import com.allison.inn.inventorymanagementsystem.model.ItemDayWiseMetric;
import com.allison.inn.inventorymanagementsystem.model.ItemMetric;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static com.allison.inn.inventorymanagementsystem.constants.InventoryManagementSystemConstants.AGED_BRIE;
import static com.allison.inn.inventorymanagementsystem.constants.InventoryManagementSystemConstants.CONCERT_BACKSTAGE_PASSES;
import static com.allison.inn.inventorymanagementsystem.constants.InventoryManagementSystemConstants.SULFURAS;


@Service
public class ItemMetricService {

    /**
     * Local repository for the item metrics
     */
    HashMap<String, ItemMetric> itemMetricMap;

    /**
     * Constructor to add special items to the repo
     */
    public ItemMetricService() {
        itemMetricMap = new HashMap<>();
        ArrayList<ItemDayWiseMetric> agedBrieDailyMetrics = new ArrayList<>();
        agedBrieDailyMetrics.add(0, new ItemDayWiseMetric( -1, 1, 0, 0));
        agedBrieDailyMetrics.add(1, new ItemDayWiseMetric(-1, 1, -1, -1));
        ItemMetric agedBrieItemMetric = new ItemMetric(agedBrieDailyMetrics);

        ArrayList<ItemDayWiseMetric> sulfurasDailyItemMetric = new ArrayList<>();
        sulfurasDailyItemMetric.add(0, new ItemDayWiseMetric(0, 0, 0, 0));
        sulfurasDailyItemMetric.add(1, new ItemDayWiseMetric(0, 0, -1, -1));
        ItemMetric sulfurasItemMetric = new ItemMetric(sulfurasDailyItemMetric);

        ArrayList<ItemDayWiseMetric> backStagePassDailyMetric = new ArrayList<>();
        backStagePassDailyMetric.add(0, new ItemDayWiseMetric(-1, 2, 0, 10));
        backStagePassDailyMetric.add(1, new ItemDayWiseMetric(-1, 1, 11, 0));
        backStagePassDailyMetric.add(2, new ItemDayWiseMetric(-1, -1000, -1, -1));
        ItemMetric backStagePassesItemMetric = new ItemMetric(backStagePassDailyMetric);

        this.itemMetricMap.put(AGED_BRIE, agedBrieItemMetric);
        this.itemMetricMap.put(SULFURAS, sulfurasItemMetric);
        this.itemMetricMap.put(CONCERT_BACKSTAGE_PASSES, backStagePassesItemMetric);
    }

    /**
     * Funciton which fetches the item Metric based on the itemName or returns default itemMetric
     * @param itemName item name for which the itemMetrics are being requested for
     * @return Item metric
     */
    public ItemMetric   getItemsMetrics(String itemName) {
        return Optional.ofNullable(this.itemMetricMap.get(itemName)).orElse(new ItemMetric());
    }
}
