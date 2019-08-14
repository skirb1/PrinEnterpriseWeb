package main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hibernateconfig.Customer;


public class HibernateTest {
	
	public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("SakilaLibraryPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Customer.findAll");
        List<Customer> resultList = query.getResultList();
        for (Customer c : resultList) {
            System.out.println("Customer Name is " + c.getFirstName() + " " + c.getLastName());
        }
    }

}
