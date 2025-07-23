package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dto.CustomDTO;
import com.example.layeredarchitecture.entity.Custom;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Custom> getAllCustomersByOrder() throws SQLException, ClassNotFoundException;
    List<Custom> getAllCustomersByItem() throws SQLException, ClassNotFoundException;
}
