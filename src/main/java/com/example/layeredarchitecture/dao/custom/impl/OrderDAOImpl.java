package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.SQLUtil;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private ItemDAOImpl itemDAO = new ItemDAOImpl();

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",
                orderId,
                orderDate,
                customerId
        );
    }

    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        try {
            connection.setAutoCommit(false);
            if (exists(orderId)){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            if (!saveOrder(orderId, orderDate, customerId)) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {
                OrderDetailDAOImpl orderDetailDAO = new OrderDetailDAOImpl();
                if (!orderDetailDAO.saveOrderDetails(new OrderDetailDTO(orderId, detail.getItemCode(), detail.getQty(), detail.getUnitPrice()))) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                if (!itemDAO.update(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()))) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ItemDTO findItem(String code) {
        try {
            ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?", code);
            if (rst.next()) {
                return new ItemDTO(code, 
                    rst.getString("description"), 
                    rst.getBigDecimal("unitPrice"), 
                    rst.getInt("qtyOnHand"));
            } else {
                throw new RuntimeException("Failed to find the Item " + code);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find the Item " + code, e);
        }
    }

    @Override
    public OrderDetailDTO search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetailDTO> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetailDTO customerTM) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetailDTO customerTM) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * FROM `Orders` WHERE oid=?", orderId);
        return rst.next();
    }

}