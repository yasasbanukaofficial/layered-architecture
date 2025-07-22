package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface CrudBO<T> extends SuperBO{
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T DTO) throws SQLException, ClassNotFoundException;
    boolean update(T DTO) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    boolean exists(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    T search(String newValue) throws SQLException, ClassNotFoundException;
    boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
    ItemDTO findItem(String code);
}
