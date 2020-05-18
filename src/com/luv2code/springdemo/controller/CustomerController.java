package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//need to inject DAO into this controller
	@Autowired
	private CustomerService customerServices;
	
	//@RequestMapping("/list")
	@GetMapping("/list")
	public String listCustomer(Model theModel)
	{
		//get the customers from DAO
		List<Customer> theCustomers = customerServices.getCustomer();
		//System.out.println("theCustomers:"+theCustomers);
		//Add to model
		theModel.addAttribute("customer", theCustomers);
		
		//return to jsp page
		return "list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		//create model attribute to bind data
		Customer theCustomer = new Customer();
		//Add the model
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer thecustomer)
	{
		//save the customer
		customerServices.saveCustomer(thecustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel)
	{
		
		//get the customer from db
		Customer theCustomer =customerServices.getCustomer(theId);
		
		//set this in model to pre-populate
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId)
	{
		
		
		customerServices.deleteCustomer(theId);
		
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String searchName,Model theModel)
	{
		
		
		//Search Customers from the Service
		List<Customer> theCustomer = customerServices.searchCustomer(searchName);
		theModel.addAttribute("customer",theCustomer);
	
		return "list-customer";
	}
	
	

}
