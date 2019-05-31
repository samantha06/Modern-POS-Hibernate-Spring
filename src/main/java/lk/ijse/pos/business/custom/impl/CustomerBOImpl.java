package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.db.HibernateUtil;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;

//    public CustomerBOImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    @Override
    public CustomerDTO getCustomerById(String id) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            customerDAO.setSession(session);
            Customer customer = customerDAO.find(id);
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
            session.getTransaction().commit();
            return customerDTO;
        }
    }

    public List<CustomerDTO> getAllCustomers() throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            customerDAO.setSession(session);
            List<CustomerDTO> customers = customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());
            session.getTransaction().commit();
            return customers;
        }
    }

    public void saveCustomer(CustomerDTO dto) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            customerDAO.setSession(session);
            customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
            session.getTransaction().commit();
        }
    }

    public void updateCustomer(CustomerDTO dto) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            customerDAO.setSession(session);
            customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
            session.getTransaction().commit();
        }
    }

    public void removeCustomer(String id) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            customerDAO.setSession(session);
            customerDAO.delete(id);
            session.getTransaction().commit();
        }
    }

}
