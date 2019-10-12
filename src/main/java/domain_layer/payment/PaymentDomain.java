package domain_layer.payment;

import entity.payment.Payment;
import exception.NotExistException;

public interface PaymentDomain {
	
	String addNewPayment(String orderID, String cardNumber, String expires, String dateAdded, double amount);
	
	void deletePayment(String id);
	
	Payment getPayment(String id) throws NotExistException;

}
