package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.db.HibernateUtil;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemBOImpl implements ItemBO {

    @Autowired
    private ItemDAO itemDAO;

    public List<ItemDTO> getAllItems() throws Exception {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            itemDAO.setSession(session);
            List<ItemDTO> items = itemDAO.findAll().stream().map(item -> new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).collect(Collectors.toList());
            session.getTransaction().commit();
            return items;
        }

    }

    public void saveItem(ItemDTO item) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            itemDAO.setSession(session);
            itemDAO.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            session.getTransaction().commit();
        }
    }

    public void updateItem(ItemDTO item) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            itemDAO.setSession(session);
            itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            session.getTransaction().commit();
        }
    }

    public void deleteItem(String code) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            itemDAO.setSession(session);
            itemDAO.delete(code);
            session.getTransaction().commit();
        }
    }

}
