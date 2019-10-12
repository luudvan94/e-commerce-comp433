package domain_layer.payment;

import dal.payment.PaymentRepository;
import dal.payment.PaymentRepositoryImpl;
import entity.payment.Payment;
import exception.NotExistException;
import util.ID;

public class PaymentDomainImpl implements PaymentDomain {
	
	private PaymentRepository paymentRepository = new PaymentRepositoryImpl();

	@Override
	public String addNewPayment(String orderID, String cardNumber, String expires, String dateAdded, double amount) {
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setCardNumber(cardNumber);
		payment.setDate_added(dateAdded);
		payment.setExpires(expires);
		payment.setOrderID(orderID);
		payment.setId(ID.generateID("CP"));
		
		return paymentRepository.create(payment);
	}

	@Override
	public void deletePayment(String id) {
		Payment payment = paymentRepository.get(id);
		
		paymentRepository.delete(payment);
		
	}

	@Override
	public Payment getPayment(String id) throws NotExistException {
		Payment payment = paymentRepository.get(id);
		
		if (payment == null) {
			throw new NotExistException("Payment with provided id does not exist");
		}
		
		return payment;
	}

}
