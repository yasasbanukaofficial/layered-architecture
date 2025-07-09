package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public interface OrderDAO {
    ItemDAOImpl itemDAO = new ItemDAOImpl();

    public String generateNewOrderId() throws SQLException, ClassNotFoundException;

    public boolean existsOrder(String orderId) throws SQLException, ClassNotFoundException;

    boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;

    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    public ItemDTO findItem(String code);
}
