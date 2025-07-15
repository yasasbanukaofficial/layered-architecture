package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.SQLUtil;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> arrayList = SQLUtil.execute("SELECT * FROM Item");
        return arrayList;
    }

    @Override
    public boolean saveItem(ItemTM itemTM) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",
                    itemTM.getCode(),
                    itemTM.getDescription(),
                    itemTM.getUnitPrice(),
                    itemTM.getQtyOnHand()
        );
    }

    @Override
    public boolean updateItem(ItemTM itemTM) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                    itemTM.getDescription(),
                    itemTM.getUnitPrice(),
                    itemTM.getQtyOnHand(),
                    itemTM.getCode()
                );
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Item WHERE code=?", code);
    }

    @Override
    public boolean existsItem(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM Item WHERE code=?", code) != null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String id = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (id != null) {
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public String getLastItemId(TableView<ItemTM> tblItems) {
        List<ItemTM> tempItemsList = new ArrayList<>(tblItems.getItems());
        Collections.sort(tempItemsList, (o1, o2) -> o1.getCode().compareTo(o2.getCode()));
        return tempItemsList.get(tempItemsList.size() - 1).getCode();
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?", code);
        if (rst.next()) {
            return new ItemDTO(
                rst.getString("code"),
                rst.getString("description"),
                rst.getBigDecimal("unitPrice"),
                rst.getInt("qtyOnHand")
            );
        }
        return null;
    }
}
