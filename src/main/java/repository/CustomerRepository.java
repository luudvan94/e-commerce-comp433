package repository;

import entity.Customer;

public interface CustomerRepository extends Repository<Customer, String> {
	Customer login(String username, String password);
	
	boolean isUserAlreadyExist(String username);
}
