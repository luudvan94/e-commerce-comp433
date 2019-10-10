package dal.order;

import java.util.List;

import dal.Repository;
import domain.order.Order1;

public interface OrderRepository extends Repository<Order1, String> {

	List<Order1> ordersByCustomerID(String id);
	List<Order1> ordersByPartnerID(String id);
	List<Order1> ordersByIDList(List<String> idList);
}
