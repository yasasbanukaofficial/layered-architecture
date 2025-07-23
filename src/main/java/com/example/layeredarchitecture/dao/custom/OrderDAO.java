package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.OrderDetail;

import java.sql.*;
import java.time.LocalDate;

public interface OrderDAO extends CrudDAO<OrderDetail> {
    boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;

    ItemDTO findItem(String code);
}
