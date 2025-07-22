package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.CrudBO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PlaceOrderBO extends CrudBO<OrderDetailDTO> {
}
