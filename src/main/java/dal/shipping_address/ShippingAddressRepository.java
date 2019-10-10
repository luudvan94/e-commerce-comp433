package dal.shipping_address;

import dal.Repository;
import domain.shipping_address.ShippingAddress;

public interface ShippingAddressRepository extends Repository<ShippingAddress, String> {

	ShippingAddress shippingAddressByCustomerID(String id);
}
