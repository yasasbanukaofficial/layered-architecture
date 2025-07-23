package com.example.layeredarchitecture.dto;

public class CustomDTO {
    private String firstName;
    private String lastName;
    private int orderId;
    private String orderDate;
    private String itemId;
    private String itemName;

    public CustomDTO() {}

    public CustomDTO(String firstName, String lastName, int orderId, String orderDate, String itemId, String itemName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public CustomDTO(String firstName, String lastName, int orderId, String orderDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public CustomDTO(String firstName, String lastName, String itemId, String itemName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }




}
