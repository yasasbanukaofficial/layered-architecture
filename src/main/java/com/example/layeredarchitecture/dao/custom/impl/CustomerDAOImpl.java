package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.SQLUtil;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<CustomerDTO> loadAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = SQLUtil.execute("SELECT * FROM Customer");
        return allCustomers;
    }

    @Override
    public boolean saveCust(CustomerTM customerTM) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                    customerTM.getId(),
                    customerTM.getName(),
                    customerTM.getAddress()
        );
    }

    @Override
    public boolean updateCust(CustomerTM customerTM) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE Customer SET name=?, address=? WHERE id=?",
                customerTM.getName(),
                customerTM.getAddress(),
                customerTM.getId()
        );
    }

    @Override
    public boolean deleteCust(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public boolean existsCustomer(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM Customer WHERE id=?", id) != null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String id = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (id != null) {
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public String getLastCustomerId(TableView<CustomerTM> tblCustomers) {
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomers.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getId();
    }

    @Override
    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?", newValue);
        if (rst.next()) {
            return new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));
        }
        return null;
    }
}
