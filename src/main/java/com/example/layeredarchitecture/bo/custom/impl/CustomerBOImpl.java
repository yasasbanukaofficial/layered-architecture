package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.CustomerBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.loadAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }
        return customerDTOS;
    }

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress()));
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exists(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    @Override
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(newValue);
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
    }
}
