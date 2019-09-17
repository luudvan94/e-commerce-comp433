package repository;

import entity.CustomerInfo;

public interface CustomerInfoRepository extends Repository<CustomerInfo, String> {
	
	CustomerInfo customerInfoByCustomerID(String id);

}
