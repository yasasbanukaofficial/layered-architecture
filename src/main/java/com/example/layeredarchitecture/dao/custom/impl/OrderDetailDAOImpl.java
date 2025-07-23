package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.dao.custom.SQLUtil;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean saveOrderDetails(OrderDetail orderDetailDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",
                orderDetailDTO.getOid(),
                orderDetailDTO.getItemCode(),
                orderDetailDTO.getUnitPrice(),
                orderDetailDTO.getQty()
        );
    }

    @Override
    public List<OrderDetail> getOrderDetails(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM OrderDetails WHERE oid=?", orderId);

        List<OrderDetail> details = new ArrayList<>();
        while (rst.next()) {
            details.add(new OrderDetail(
                    rst.getString("itemCode"),
                    rst.getInt("qty"),
                    rst.getBigDecimal("unitPrice")
            ));
        }
        return details;
    }

    @Override
    public boolean existsOrderDetail(String orderId, String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM OrderDetails WHERE oid=? AND itemCode=?", orderId, itemCode);
        return rst.next();
    }

    @Override
    public ArrayList<OrderDetail> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public OrderDetail search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}