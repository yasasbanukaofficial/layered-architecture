package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.QueryDAO;
import com.example.layeredarchitecture.dto.CustomDTO;
import com.example.layeredarchitecture.entity.Custom;

import java.sql.SQLException;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Custom> getAllCustomersByOrder() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Custom> getAllCustomersByItem() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
