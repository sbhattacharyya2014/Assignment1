package orderDetails;

import java.util.List;

public class Order {
	private String orderId;
	private List<String> itemsInOrder;
	public Order(String orderId, List<String> itemsInOrder) {
		this.orderId=orderId;
		this.itemsInOrder=itemsInOrder;
		
	}
	public String getOrderId() {
		return orderId;
	}
	public List<String> getItemsInOrder() {
		return itemsInOrder;
	}

}
