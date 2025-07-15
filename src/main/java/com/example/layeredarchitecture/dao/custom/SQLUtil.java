package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... ob) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        for (int i = 0; i < ob.length; i++) {
            stm.setObject(i + 1, ob[i]);
        }
        if (sql.startsWith("select") || sql.startsWith("SELECT")){
            ResultSet rst = stm.executeQuery();
            return (T) rst;
        } else {
            int i = stm.executeUpdate();
            return (T) (Boolean) (i > 0);
        }
    }
}
