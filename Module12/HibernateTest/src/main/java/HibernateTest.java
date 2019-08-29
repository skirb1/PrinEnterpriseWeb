package main.java;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.spi.PersistenceProvider;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernatePersistenceProvider;

import main.entities.Address;
import main.entities.Customer;
import main.entities.CustomerHome;
import main.entities.Store;


public class HibernateTest {
	
	public static void main(String[] args) {
		PersistenceProvider provider = new HibernatePersistenceProvider();
        EntityManager entityManager = provider.createEntityManagerFactory("HibernateTest", null).createEntityManager();
        CustomerService custService = new CustomerService(entityManager);
        
        Address addr = getAddress(entityManager);
        Store store = getStore(entityManager);
        String firstName = "TestFirst";
        String lastName = "TestLast";
        
        Customer cust = new Customer(addr, store, firstName, lastName, true, new Date());
        custService.persist(cust);
        
        List<Customer> resultList = custService.findAll();
        for (Customer c : resultList) {
            System.out.println("Customer Name is " + c.getFirstName() + " " + c.getLastName());
        }
        
        resultList = custService.findByFirstName(firstName);
        System.out.println("Retrieved " + resultList.size() + " customers named " + firstName);
        
        
    }
	
	public static Address getAddress(EntityManager em) {
		Query query = em.createNativeQuery("select * from address", Address.class);
		return (Address) query.getResultList().get(0);
	}
	
	public static Store getStore(EntityManager em) {
		Query query = em.createNativeQuery("select * from store", Store.class);
		return (Store) query.getResultList().get(0);
	}

}
