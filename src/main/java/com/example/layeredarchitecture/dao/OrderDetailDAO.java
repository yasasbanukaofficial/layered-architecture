package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDetailDAO {
    public boolean saveOrderDetails(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;

    public List<OrderDetailDTO> getOrderDetails(String orderId) throws SQLException, ClassNotFoundException;

    public boolean existsOrderDetail(String orderId, String itemCode) throws SQLException, ClassNotFoundException;
}
