package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetailDTO> {
    public boolean saveOrderDetails(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;

    public List<OrderDetailDTO> getOrderDetails(String orderId) throws SQLException, ClassNotFoundException;

    public boolean existsOrderDetail(String orderId, String itemCode) throws SQLException, ClassNotFoundException;
}
