package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.QueryDAO;
import com.example.layeredarchitecture.model.CustomDTO;

import java.sql.SQLException;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomDTO> getAllCustomersByOrder() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<CustomDTO> getAllCustomersByItem() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
