package com.allison.inn.inventorymanagementsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class ItemDayWiseMetric {
    @Id
    public String name;
    public int sellInAdjuster;
    public int qualityAdjuster;
    public int minDayAdjuster;
    public int maxDayAdjuster;

    /**
     * Default constructor
     */
    public ItemDayWiseMetric() {
        this.sellInAdjuster = -1;
        this.qualityAdjuster = -1;
        this.minDayAdjuster = 0;
        this.maxDayAdjuster = 0;
    }

    /**
     * Constructor to initialize the full object
     * @param sellInAdjuster
     * @param qualityAdjuster
     * @param minDayAdjuster
     * @param maxDayAdjuster
     */

    public ItemDayWiseMetric(int sellInAdjuster, int qualityAdjuster, int minDayAdjuster, int maxDayAdjuster) {
        this.sellInAdjuster = sellInAdjuster;
        this.qualityAdjuster = qualityAdjuster;
        this.minDayAdjuster = minDayAdjuster;
        this.maxDayAdjuster = maxDayAdjuster;
    }
}
