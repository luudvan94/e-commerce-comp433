package dal.customer;

import dal.Repository;
import entity.customer.Customer;

public interface CustomerRepository extends Repository<Customer, String> {
	Customer customerByUsernamePassword(String username, String password);
	
	boolean isUserAlreadyExist(String username);
}
