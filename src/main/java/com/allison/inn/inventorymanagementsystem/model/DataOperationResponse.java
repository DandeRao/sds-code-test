package com.allison.inn.inventorymanagementsystem.model;

import lombok.Getter;
import lombok.Setter;

import static com.allison.inn.inventorymanagementsystem.constants.InventoryManagementSystemConstants.OPERATION_SUCCESS_MESSAGE;

@Getter @Setter
public class DataOperationResponse {
    /**
     * boolean indicating if operation is successful
     */
    public boolean success;
    /**
     * Messages indicating the operation status
     */
    public String message;

    public DataOperationResponse() {
        this.success = true;
        this.message = OPERATION_SUCCESS_MESSAGE;
    }
}
