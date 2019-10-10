package dal.payment;

import dal.Repository;
import domain.payment.Payment;

public interface PaymentRepository extends Repository<Payment, String> {

	Payment cardProfileByOrderID(String id);
}
