package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.PlaceOrderBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    private OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    private OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    public boolean save(OrderDetailDTO DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean update(OrderDetailDTO DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    public OrderDetailDTO search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        try {
            connection.setAutoCommit(false);
            if (orderDAO.exists(orderId)){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            if (!orderDAO.saveOrder(orderId, orderDate, customerId)) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {
                if (!orderDetailDAO.saveOrderDetails(new OrderDetail(orderId, detail.getItemCode(), detail.getQty(), detail.getUnitPrice()))) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                if (!itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()))) {
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

    public ItemDTO findItem(String code) {
        return orderDAO.findItem(code);
    }
}
