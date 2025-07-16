package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL, QUERY;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                new CustomerDAOImpl();
            case ITEM:
                new ItemDAOImpl();
            case ORDER:
                new OrderDAOImpl();
            case ORDER_DETAIL:
                new OrderDetailDAOImpl();
            case QUERY:
                new QueryDAOImpl();

            default:
                return null;
        }
    }
}
