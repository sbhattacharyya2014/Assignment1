package orderDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDetails {

	List<Order> orders;

	public OrderDetails(List<Order> orders) {
		this.orders = orders;
	}

	public List<String> findItem(String item) {
		// This method will fetch all orders for each item

		List<String> ordersWithItem = this.orders.stream().filter(ord -> ord.getItemsInOrder().contains(item))
				.map(Order::getOrderId)
				.collect(Collectors.toList());
		return ordersWithItem;

	}

	public void highestFrequency() {
		Map<String, List<String>> itemInOrders = new HashMap<String, List<String>>();
		List<String> items = new ArrayList<String>();
		//Finding the unique items in all orders
		try {
			for (Order od : orders) {
				for (String item : od.getItemsInOrder()) {
					if (!items.contains(item))
						items.add(item);
				}
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("An order item can not be null! Please check the input");
			System.exit(1);
		}
		//Adding the items and orders as key value pair
		for (String item : items) {
			itemInOrders.put(item, findItem(item));
		}
		
		for(int i=0; i<2;i++) {
			String maxOrderItem = itemInOrders.entrySet().stream()
					.max((entry1, entry2) -> 
					entry1.getValue().size() > entry2.getValue().size() ? 1 : -1)
					.get().getKey();
			//Creating output string by adding delimitor
			String outputStr=maxOrderItem+" -> usageCount: "+itemInOrders.get(maxOrderItem).size()
					+ "; Corresponding Orders :"
					+itemInOrders.get(maxOrderItem).stream().collect(Collectors.joining(", "));
			
			System.out.println(outputStr);
			
			
			//Removing the highest ordered item
			itemInOrders.remove(maxOrderItem);
			
		}


	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Order od1 = new Order("order 1", Arrays.asList("item 1", "item 2"));
		Order od2 = new Order("order 2", Arrays.asList("item 3"));
		Order od3 = new Order("order 3", Arrays.asList("item 2"));
		Order od4 = new Order("order 4", Arrays.asList("item 1", "item 2"));
		OrderDetails oDetails = new OrderDetails(new ArrayList<Order>(Arrays.asList(od1, od2, od3,od4)));
		Order od5 = new Order("order 5", Arrays.asList("item 1"));
		oDetails.addOrder(od5);
		oDetails.highestFrequency();

	}

}
