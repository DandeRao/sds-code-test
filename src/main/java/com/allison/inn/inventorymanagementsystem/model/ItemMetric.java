package com.allison.inn.inventorymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ItemMetric {
    @JsonProperty("itemName")
    public String itemName;
    @JsonProperty("itemMetric")
    public ArrayList<ItemDayWiseMetric> itemMetric;

    public ItemMetric() {
        this.itemName = "";
        this.itemMetric = new ArrayList<>(10);
        this.itemMetric.add(new ItemDayWiseMetric());
    }

    public  ItemMetric(ArrayList<ItemDayWiseMetric> itemMetric) {
        this.itemName = "";
        this.itemMetric = itemMetric;
    }
}
