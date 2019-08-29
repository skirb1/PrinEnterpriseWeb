package main.java;

import main.entities.Customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CustomerService {
	
	EntityManager em = null;
	
	public CustomerService(EntityManager em) {
		this.em = em;
	}
	
	public List<Customer> findByFirstName(String name) {
		List<Customer> results = null;
		Query query = em.createNativeQuery("select * from customer where first_name='"+name+"'", Customer.class);
		results = query.getResultList();
		return results;
	}
	
	public List<Customer> findAll(){
		List<Customer> results = null;
		Query query = em.createNativeQuery("select * from customer", Customer.class);
		results = query.getResultList();
		return results;
	}
	
	public void persist(Customer cust) {
		em.getTransaction().begin();
		em.persist(cust);
		em.getTransaction().commit();
	}

}
