package dal.customer;

import dal.Repository;
import domain.customer.Customer;

public interface CustomerRepository extends Repository<Customer, String> {
	Customer login(String username, String password);
	
	boolean isUserAlreadyExist(String username);
}
