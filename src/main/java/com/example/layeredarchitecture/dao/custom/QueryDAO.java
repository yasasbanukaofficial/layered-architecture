package com.example.layeredarchitecture.dao.custom;

import java.sql.SQLException;

public interface QueryDAO {
    void getAllCustomersByOrder() throws SQLException, ClassNotFoundException;
}
