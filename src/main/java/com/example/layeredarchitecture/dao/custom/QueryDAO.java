package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.CustomDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO {
    List<CustomDTO> getAllCustomersByOrder() throws SQLException, ClassNotFoundException;
    List<CustomDTO> getAllCustomersByItem() throws SQLException, ClassNotFoundException;
}
