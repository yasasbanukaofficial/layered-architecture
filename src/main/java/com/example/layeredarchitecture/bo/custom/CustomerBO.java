package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    boolean exists(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException;
}
