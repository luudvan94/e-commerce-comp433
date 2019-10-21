package domain_layer.payment;

import java.util.Date;

import dal.payment.PaymentRepository;
import dal.payment.PaymentRepositoryImpl;
import entity.payment.Payment;
import exception.NotExistException;
import util.ID;

public class PaymentDomainImpl implements PaymentDomain {
	
	private PaymentRepository paymentRepository = new PaymentRepositoryImpl();

	@Override
	public Payment addNewPayment(String cardNumber, String expires, double amount) {
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setCardNumber(cardNumber);
		payment.setDate_added(Long.toString(new Date().getTime()));
		payment.setExpires(expires);
		payment.setPaymentID(ID.generateID("CP"));
		
		paymentRepository.create(payment);
		
		return payment;
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
