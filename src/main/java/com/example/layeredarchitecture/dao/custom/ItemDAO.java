package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException;

    public boolean saveItem(ItemTM itemTM) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemTM itemTM) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

    public boolean existsItem(String code) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public String getLastItemId(TableView<ItemTM> tblItems);

    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;
}
