package domain_layer.payment;

import entity.payment.Payment;
import exception.NotExistException;

public interface PaymentDomain {
	
	Payment addNewPayment(String cardNumber, String expires, double amount);
	
	Payment getPayment(String id) throws NotExistException;

}
