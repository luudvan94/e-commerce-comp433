package repository;

import entity.ShippingAddress;

public interface ShippingAddressRepository extends Repository<ShippingAddress, String> {

	ShippingAddress shippingAddressByCustomerID(String id);
}
