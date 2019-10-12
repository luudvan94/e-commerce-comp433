package dal.customer;

import dal.Repository;
import entity.customer.CustomerInfo;

public interface CustomerInfoRepository extends Repository<CustomerInfo, String> {
	
	CustomerInfo customerInfoByCustomerID(String id);

}
