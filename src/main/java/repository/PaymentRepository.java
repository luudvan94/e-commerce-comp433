package repository;

import entity.Payment;

public interface PaymentRepository extends Repository<Payment, String> {

	Payment cardProfileByOrderID(String id);
}
