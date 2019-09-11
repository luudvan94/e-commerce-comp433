package entity;

import java.util.List;

import org.javatuples.Pair;


public class Order {

	private int id;
	
	private int totalPrice;
	
	private String status;
	
	private List<Pair<Book, Integer>> products;
	
	private ShippingAddress shippingAddress;
	
	private CardPayment cardPayment;
	
	public Order() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public CardPayment getCardPayment() {
		return cardPayment;
	}

	public void setCardPayment(CardPayment cardPayment) {
		this.cardPayment = cardPayment;
	}

	public List<Pair<Book, Integer>> getProducts() {
		return products;
	}

	public void setProducts(List<Pair<Book, Integer>> products) {
		this.products = products;
	}
	
}
