package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Customer;
import org.springframework.stereotype.Component;


public interface CustomerDAO extends CrudDAO<Customer,String> {

    int count() throws Exception;

}
