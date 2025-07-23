package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.SQLUtil;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList <Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address")));
        }
        return customers;
    }

    @Override
    public boolean save(Customer customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
    }

    @Override
    public boolean update(Customer customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?", customerDTO.getName(), customerDTO.getAddress(), customerDTO.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?", id);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer WHERE id=?", newValue + "");
        if (rst.next()) {
            return new Customer(newValue + "", rst.getString("name"), rst.getString("address"));
        }
        return null;
    }
}
