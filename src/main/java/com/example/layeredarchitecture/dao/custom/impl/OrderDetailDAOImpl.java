package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean saveOrderDetails(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
        stm.setString(1, orderDetailDTO.getOid());
        stm.setString(2, orderDetailDTO.getItemCode());
        stm.setBigDecimal(3, orderDetailDTO.getUnitPrice());
        stm.setInt(4, orderDetailDTO.getQty());

        if (stm.executeUpdate() != 1) {
            return false;
        }
        return true;
    }

    @Override
    public List<OrderDetailDTO> getOrderDetails(String orderId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM OrderDetails WHERE oid=?");
        stm.setString(1, orderId);
        ResultSet rst = stm.executeQuery();

        List<OrderDetailDTO> details = new ArrayList<>();
        while (rst.next()) {
            details.add(new OrderDetailDTO(
                    rst.getString("itemCode"),
                    rst.getInt("qty"),
                    rst.getBigDecimal("unitPrice")
            ));
        }
        return details;
    }

    @Override
    public boolean existsOrderDetail(String orderId, String itemCode) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM OrderDetails WHERE oid=? AND itemCode=?");
        stm.setString(1, orderId);
        stm.setString(2, itemCode);
        return stm.executeQuery().next();
    }
}