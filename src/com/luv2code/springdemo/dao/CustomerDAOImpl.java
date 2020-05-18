package com.luv2code.springdemo.dao;

import java.util.List;

//import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository // component scan for DAO impl
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject session factory
	@Autowired // sessionFactory bean Name
	private SessionFactory sessionFactory;

	@Override
	// @Transactional // auto begin transation and commit it
	public List<Customer> getCustomers() {
		// get current hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();

		// create query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);

		// execute query and get result set
		List<Customer> customers = theQuery.getResultList();

		// return the rstult
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();
		// save in db
		currentSession.saveOrUpdate(theCustomer);

	}

	@Override
	public Customer getCustomer(int theId) {
		// get current hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
	
		// get current hibernate Session
				Session currentSession = sessionFactory.getCurrentSession();
				//delete the object using query.
				Query theQuery=currentSession.createQuery("delete from Customer where id=:customeId");
				theQuery.setParameter("customeId",theId);
				theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomer(String searchName) {
		// get current hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();
		
		 Query theQuery = null;
		 // only search by name if theSearchName is not empty
		 if (searchName != null && searchName.trim().length() > 0) {

	            // search for firstName or lastName ... case insensitive
	            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	            theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");

	        }
		  else {
	            // theSearchName is empty ... so just get all customers
	            theQuery =currentSession.createQuery("from Customer", Customer.class);            
	        }
		 
		// execute query and get result list
	        List<Customer> customers = theQuery.getResultList();
	        // return the results        
	        return customers;
	}

}
