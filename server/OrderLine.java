package server;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */
public class OrderLine {
	
	/**
	 * Item object
	 */
	private Item theItem;
	
	/**
	 * order quantity
	 */
	private int orderQuantity;
	
	/**
	 * Constructs an OrderLine object
	 * @param item an Item object
	 * @param quantity the item's quantity
	 */
	public OrderLine (Item item, int quantity) {
		theItem = item;
		setOrderQuantity(quantity); 
	}

	/**
	 * getter function for the Item
	 * @return returns an Item object
	 */
	public Item getTheItem() {
		return theItem;
	}

	/**
	 * setter function for the Item
	 * @param theItem an Item object
	 */
	public void setTheItem(Item theItem) {
		this.theItem = theItem;
	}

	/**
	 * getter function for order quantity
	 * @return returns the order quantity
	 */
	public int getOrderQuantity() {
		return orderQuantity;
	}

	/**
	 * setter function for order quantity
	 * @param orderQuantity the order quantity
	 */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	/**
	 * toString function for the OrderLine class
	 */
	public String toString (){
		return  "Item Name: " + theItem.getItemName() +
				", Item ID: " + theItem.getItemId()+ "\n" + 
				"Order Quantity: " + orderQuantity + "\n";
	}

}
