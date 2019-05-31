package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.entity.OrderDetailPK;
import org.springframework.stereotype.Component;


public interface OrderDetailDAO extends CrudDAO<OrderDetail, OrderDetailPK> {
    
}
