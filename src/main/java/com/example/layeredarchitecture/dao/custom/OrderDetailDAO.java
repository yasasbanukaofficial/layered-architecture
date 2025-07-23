package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {
    public boolean saveOrderDetails(OrderDetail orderDetailDTO) throws SQLException, ClassNotFoundException;

    public List<OrderDetail> getOrderDetails(String orderId) throws SQLException, ClassNotFoundException;

    public boolean existsOrderDetail(String orderId, String itemCode) throws SQLException, ClassNotFoundException;
}
