package com.allison.inn.inventorymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Item {

    /**
     * Sell in days
     */
    @JsonProperty("sellIn")
    public int sellIn;

    /**
     * Quality of the item
     */
    @JsonProperty("quality")
    public int quality;

    /**
     * Name of the item
     */
    @Id
    @JsonProperty("name")
    public String name;

    public Item() {
    }

    /**
     * Constructor to initialize full object
     * @param sellIn
     * @param quality
     * @param name
     */
    public Item(int sellIn, int quality, String name) {
        this.sellIn = sellIn;
        this.quality = quality;
        this.name = name;
    }
}
