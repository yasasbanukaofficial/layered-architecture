package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface CustomerDAO {
    public ArrayList<CustomerDTO> loadAllCustomers() throws SQLException, ClassNotFoundException;

    public boolean saveCust(CustomerTM customerTM) throws SQLException, ClassNotFoundException;

    public boolean updateCust(CustomerTM customerTM) throws SQLException, ClassNotFoundException;

    public boolean deleteCust(String id) throws SQLException, ClassNotFoundException;

    public boolean existsCustomer(String id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public String getLastCustomerId(TableView<CustomerTM> tblCustomers) throws SQLException, ClassNotFoundException;

    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException;
}
