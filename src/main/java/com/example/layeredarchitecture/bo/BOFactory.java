package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.bo.custom.impl.CustomerBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory == null? boFactory = new BOFactory(): boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEMS, PLACE_ORDERS
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEMS:
                return new ItemBOImpl();
            case PLACE_ORDERS:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
