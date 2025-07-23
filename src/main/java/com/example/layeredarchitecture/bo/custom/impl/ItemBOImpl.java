package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.ItemBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.loadAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        }
        return itemDAO.loadAll();
    }

    @Override
    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(itemDTO);
    }

    @Override
    public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(itemDTO);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exists(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }

    @Override
    public ItemDTO search(String newValue) throws SQLException, ClassNotFoundException {
        return itemDAO.search(newValue);
    }
}
