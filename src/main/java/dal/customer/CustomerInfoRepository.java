package dal.customer;

import dal.Repository;
import domain.customer.CustomerInfo;

public interface CustomerInfoRepository extends Repository<CustomerInfo, String> {
	
	CustomerInfo customerInfoByCustomerID(String id);

}
