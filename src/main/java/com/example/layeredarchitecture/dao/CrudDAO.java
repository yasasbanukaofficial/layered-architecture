package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    public ArrayList<CustomerDTO> loadAll() throws SQLException, ClassNotFoundException;

    public boolean save(T customerTM) throws SQLException, ClassNotFoundException;

    public boolean update(T customerTM) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean exists(String id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException;
}
