package repository;

import java.util.List;

import entity.Order1;

public interface OrderRepository extends Repository<Order1, String> {

	List<Order1> ordersByCustomerID(String id);
	List<Order1> ordersByPartnerID(String id);
	List<Order1> ordersByIDList(List<String> idList);
}
