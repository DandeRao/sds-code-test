package com.allison.inn.inventorymanagementsystem.business;

import com.allison.inn.inventorymanagementsystem.model.Item;
import com.allison.inn.inventorymanagementsystem.model.ItemMetric;
import com.allison.inn.inventorymanagementsystem.services.ItemInventoryService;
import com.allison.inn.inventorymanagementsystem.services.ItemMetricService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class InventoryStatusCalculatorTests {

    @InjectMocks
    InventoryStatusCalculator inventoryStatusCalculator;

    @Mock
    ItemInventoryService itemInventoryService;

    ItemMetricService itemMetricService;

    @Mock
    InventoryManger inventoryManger;

    @Before
    public void setUp() throws Exception {
        itemMetricService = new ItemMetricService();
    }

    @Test
    public void shouldReturnEmptyItemStatusWhenNoItemsAreInInventory() {

        Mockito.when(itemInventoryService.getItemsInInventory())
                .thenReturn(new ArrayList<>());

        List<Item> itemStatus = inventoryStatusCalculator.getInventoryStatusAfterDays(5);
        assertEquals(0, itemStatus.size());
    }

    @Test
    public void shouldTestTheItemWithDefaultMetricWhenItemHasNoItemMetric() {

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(10, 20, "+5 Dexterity Vest"));

        ItemMetric im = new ItemMetric();


        Mockito.when(itemInventoryService.getItemsInInventory())
                .thenReturn(itemList);

        Mockito.when(inventoryManger.getItemMetrics(Mockito.anyString()))
                .thenReturn(im);

        List<Item> itemStatus = inventoryStatusCalculator.getInventoryStatusAfterDays(5);
        assertEquals(1, itemStatus.size());
        assertEquals(15, itemStatus.get(0).getQuality());
        assertEquals(5, itemStatus.get(0).getSellIn());
    }

    @Test
    public void shouldTestTheItemWithSpecialMetricWhenSpecialMetricIsAvailable() {

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(2, 0, "Aged Brie"));

        Mockito.when(itemInventoryService.getItemsInInventory())
                .thenReturn(itemList);

        Mockito.when(inventoryManger.getItemMetrics(Mockito.anyString()))
                .thenReturn(itemMetricService.getItemsMetrics("Aged Brie"));

        List<Item> itemStatus = inventoryStatusCalculator.getInventoryStatusAfterDays(5);
        assertEquals(8, itemStatus.get(0).getQuality());
        assertEquals(-3, itemStatus.get(0).getSellIn());
    }

    @Test
    public void shouldTestTheItemWithDefaultMetricWhenItemSellInDateIsLessThanFutureDate() {

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(5, 7, "Elixir of the Mongoose"));
        Mockito.when(itemInventoryService.getItemsInInventory())
                .thenReturn(itemList);

        Mockito.when(inventoryManger.getItemMetrics(Mockito.anyString()))
                .thenReturn(new ItemMetric());

        List<Item> itemStatus = inventoryStatusCalculator.getInventoryStatusAfterDays(5);
        assertEquals(2, itemStatus.get(0).getQuality());
        assertEquals(0, itemStatus.get(0).getSellIn());
    }

    @Test
    public void shouldTestSpecialItemWithMultipleMetricsForMediumRange() {

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(15, 20, "Concert backstage passes"));
        Mockito.when(itemInventoryService.getItemsInInventory())
                .thenReturn(itemList);

        Mockito.when(inventoryManger.getItemMetrics(Mockito.anyString()))
                .thenReturn(itemMetricService.getItemsMetrics("Concert backstage passes"));

        List<Item> itemStatus = inventoryStatusCalculator.getInventoryStatusAfterDays(5);
        assertEquals(26, itemStatus.get(0).getQuality());
        assertEquals(10, itemStatus.get(0).getSellIn());
    }

    @Test
    public void shouldTestSpecialItemWithMultipleMetricsForMaximumRange() {

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(9, 20, "Concert backstage passes"));
        Mockito.when(itemInventoryService.getItemsInInventory())
                .thenReturn(itemList);

        Mockito.when(inventoryManger.getItemMetrics(Mockito.anyString()))
                .thenReturn(itemMetricService.getItemsMetrics("Concert backstage passes"));

        List<Item> itemStatus = inventoryStatusCalculator.getInventoryStatusAfterDays(5);
        assertEquals(30, itemStatus.get(0).getQuality());
        assertEquals(4, itemStatus.get(0).getSellIn());
    }

    @Test
    public void shouldTestBackStagePassValueWhenTheyAreExpired() {

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, 20, "Concert backstage passes"));
        Mockito.when(itemInventoryService.getItemsInInventory())
                .thenReturn(itemList);

        Mockito.when(inventoryManger.getItemMetrics(Mockito.anyString()))
                .thenReturn(itemMetricService.getItemsMetrics("Concert backstage passes"));

        List<Item> itemStatus = inventoryStatusCalculator.getInventoryStatusAfterDays(5);
        assertEquals(0, itemStatus.get(0).getQuality());
        assertEquals(-4, itemStatus.get(0).getSellIn());
    }

}
