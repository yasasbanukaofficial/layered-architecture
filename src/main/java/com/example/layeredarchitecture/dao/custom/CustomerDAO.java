package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerTM> {
    public ArrayList<CustomerDTO> loadAll() throws SQLException, ClassNotFoundException;

    public boolean save(CustomerTM customerTM) throws SQLException, ClassNotFoundException;

    public boolean update(CustomerTM customerTM) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean exists(String id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public String getLastCustomerId(TableView<CustomerTM> tblCustomers) throws SQLException, ClassNotFoundException;

    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException;
}
