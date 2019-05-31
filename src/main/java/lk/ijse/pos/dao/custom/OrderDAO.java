package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Order;
import org.springframework.stereotype.Component;


public interface OrderDAO extends CrudDAO<Order, Integer> {

    int getLastOrderId()throws Exception;
}
