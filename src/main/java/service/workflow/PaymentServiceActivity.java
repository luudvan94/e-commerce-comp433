package service.workflow;

import domain_layer.payment.PaymentDomain;
import domain_layer.payment.PaymentDomainImpl;
import entity.payment.Payment;
import exception.NotExistException;
import representation.PaymentRepresentation;
import representation.PaymentRequest;
import service.util.RepresentationConverter;

public class PaymentServiceActivity {
	
	private PaymentDomain paymentDomain = new PaymentDomainImpl();
	
	public PaymentRepresentation get(String id) throws NotExistException {
		Payment payment = paymentDomain.getPayment(id);
		
		return RepresentationConverter.toPaymentRepresentation(payment.getPaymentID(), payment.getCardNumber(), payment.getExpires(), payment.getAmount(), payment.getDate_added());
	}
	
	public PaymentRepresentation createNewPayment(PaymentRequest request) {
		Payment payment = paymentDomain.addNewPayment(request.getCardNumber(), request.getExpires(), request.getAmount());
		
		return RepresentationConverter.toPaymentRepresentation(payment.getPaymentID(), payment.getCardNumber(), payment.getExpires(), payment.getAmount(), payment.getDate_added());
	}

}
